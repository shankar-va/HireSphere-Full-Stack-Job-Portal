package com.naukri.driver.service.impl;

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

@Service
@RequiredArgsConstructor
public class JobSeekerService {
	private final UserRepository userRepository;
	private final JobSeekerRepository jobSeekerRepository;
	private final JobSeekerMapper jobSeekerMapper;
	private final ResumeRepository resumeRepository;
	
	@Transactional
	public JobSeekerResponse registerJobSeeker(JobSeekerRegistrationRequest request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new UserNotFoundException("User Not Found."));
		if(jobSeekerRepository.existsByUserUserId(request.getUserId()))throw new JobSeekerAlreadyExists("User already has JobSeeker profile");
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
}
