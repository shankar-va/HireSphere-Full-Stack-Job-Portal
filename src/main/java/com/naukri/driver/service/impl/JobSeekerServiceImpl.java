package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.jobseeker.JobSeekerSearchRequest;
import com.naukri.driver.dto.response.jobseeker.JobSeekerSummaryResponse;
import com.naukri.driver.exception.customExceptions.jobSeeker.JobSeekerInvalidSortingException;
import com.naukri.driver.specification.jobSeeker.BuildJobSeekerSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naukri.driver.dto.request.jobseeker.JobSeekerRegistrationRequest;
import com.naukri.driver.dto.request.jobseeker.JobSeekerUpdateRequest;
import com.naukri.driver.dto.response.jobseeker.JobSeekerResponse;
import com.naukri.driver.exception.customExceptions.jobSeeker.JobSeekerAlreadyExists;
import com.naukri.driver.exception.customExceptions.jobSeeker.JobSeekerNotFoundException;
import com.naukri.driver.exception.customExceptions.resume.ResumeNotFoundException;
import com.naukri.driver.exception.customExceptions.resume.ResumeOwnershipException;
import com.naukri.driver.exception.customExceptions.user.UserNotFoundException;
import com.naukri.driver.mapper.jobSeeker.JobSeekerMapper;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.model.entity.Resume;
import com.naukri.driver.model.entity.User;
import com.naukri.driver.repository.JobSeekerRepository;
import com.naukri.driver.repository.ResumeRepository;
import com.naukri.driver.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl {
	private final UserRepository userRepository;
	private final JobSeekerRepository jobSeekerRepository;
	private final JobSeekerMapper jobSeekerMapper;
	private final ResumeRepository resumeRepository;
	private final BuildJobSeekerSpecification buildJobSeekerSpecification;
	@Transactional
	public JobSeekerResponse registerJobSeeker(JobSeekerRegistrationRequest request,Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found."));
		if(jobSeekerRepository.existsByUserUserId(userId))throw new JobSeekerAlreadyExists("User already has JobSeeker profile");
		JobSeeker jobSeeker = jobSeekerMapper.toEntity(request, user);
		JobSeeker newJobSeeker = jobSeekerRepository.save(jobSeeker);
		return jobSeekerMapper.toResponseDTO(newJobSeeker);
	}
	public JobSeekerResponse getJobSeekerById(Integer id) {
		JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(()->new JobSeekerNotFoundException("Job Seeker Not Found."));
		return jobSeekerMapper.toResponseDTO(jobSeeker);
	}
	@Transactional
	public void deleteJobSeeker(Integer id) {
		JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(()->new JobSeekerNotFoundException("Job Seeker Not Found."));
		jobSeekerRepository.delete(jobSeeker);
	}
	@Transactional
	public JobSeekerResponse updateJobSeeker(JobSeekerUpdateRequest request) {

	    JobSeeker jobSeeker = jobSeekerRepository.findById(request.getJobSeekerId())
	            .orElseThrow(() ->
	                    new JobSeekerNotFoundException("Job Seeker Not Found."));
	    if (request.getResumeId() != null) {
	         
	         Resume resume = resumeRepository.findById(request.getResumeId())
	                 .orElseThrow(()->new ResumeNotFoundException("Resume Not Found."));
	         if (!resume.getJobSeeker().getJobSeekerId()
	        	        .equals(jobSeeker.getJobSeekerId())) {

	        	    throw new ResumeOwnershipException(
	        	            "Resume does not belong to this Job Seeker");
	        	}
	         jobSeeker.setResume(resume);
	    }
	    JobSeeker updatedJobSeeker =
	            jobSeekerMapper.jobSeekerUpdateRequest(request, jobSeeker);
	    
	    JobSeeker savedJobSeeker =
	            jobSeekerRepository.save(updatedJobSeeker);

	    return jobSeekerMapper.toResponseDTO(savedJobSeeker);
	}
	public Page<JobSeekerSummaryResponse> searchJobSeekers(
			JobSeekerSearchRequest request,
			Integer page,
			Integer size,
			String sort,
			String sortDirection) {

		Specification<JobSeeker> specification =
				buildJobSeekerSpecification
						.buildJobSeekerSpecification(request);

		List<String> sortOrder = List.of(
				"jobSeekerId",
				"experience",
				"currentSalary",
				"expectedSalary"
		);

		if (sort == null || !sortOrder.contains(sort)) {
			sort = "jobSeekerId";
		}

		if (sortDirection == null || sortDirection.isBlank()) {
			sortDirection = "asc";
		}

		if (!sortDirection.equalsIgnoreCase("asc")
				&& !sortDirection.equalsIgnoreCase("desc")) {

			throw new JobSeekerInvalidSortingException(
					"Invalid sorting direction");
		}

		if (page == null || page < 0) {
			page = 0;
		}

		if (size == null || size <= 0) {
			size = 10;
		}

		Pageable pageable = PageRequest.of(
				page,
				size,
				Sort.by(sort, sortDirection)
		);

		return jobSeekerRepository
				.findAll(specification, pageable)
				.map(jobSeekerMapper::toResponseDTOSummary);
	}
}
