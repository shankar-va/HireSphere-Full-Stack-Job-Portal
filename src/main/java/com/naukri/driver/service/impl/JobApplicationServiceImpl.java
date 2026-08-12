package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.jobApplication.ApplicationStatusUpdateRequest;
import com.naukri.driver.dto.request.jobApplication.ApplyForJobRequest;
import com.naukri.driver.dto.request.jobApplication.JobApplicationSearchRequest;
import com.naukri.driver.dto.response.jobApplication.JobApplicationResponse;
import com.naukri.driver.dto.response.jobApplication.JobApplicationSummaryResponse;
import com.naukri.driver.enumaration.jobApplication.ApplicationStatus;
import com.naukri.driver.exception.customExceptions.job.JobNotFoundException;
import com.naukri.driver.exception.customExceptions.jobApplication.*;
import com.naukri.driver.exception.customExceptions.jobSeeker.JobSeekerNotFoundException;
import com.naukri.driver.mapper.jobApplication.JobApplicationMapper;
import com.naukri.driver.model.entity.Job;
import com.naukri.driver.model.entity.JobApplication;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.repository.JobApplicationRepository;
import com.naukri.driver.repository.JobRepository;
import com.naukri.driver.repository.JobSeekerRepository;
import com.naukri.driver.specification.jobApplication.BuildJobApplicationSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl {
    private final JobRepository jobRepository;
    private final JobApplicationMapper jobApplicationMapper;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final BuildJobApplicationSpecification jobApplicationSpecification;
    @Transactional
    public JobApplicationResponse applyForJob(ApplyForJobRequest request){
        Job job = jobRepository.findById(request.getJobId())
                                        .orElseThrow(() -> new JobNotFoundException("Job Not Found"));
        JobSeeker jobSeeker = jobSeekerRepository.findById(request.getJobseekerId())
                                                          .orElseThrow(() -> new JobSeekerNotFoundException("Job Seeker Not found"));

        if(job.getIsClosed())throw new JobApplicationClosedException("Job Application Closed");
        if(job.getApplicationDeadLine().isBefore(LocalDate.now()))throw new JobApplicationCrossedDeadlineException("Job Application crossed Deadline");
        if(request.getExpectedJoinDate()!=null){
            if(request.getExpectedJoinDate().isBefore(LocalDate.now()))throw new JobApplicationPastJoinDateException("Join date Cannot be in past");
        }
        if(jobApplicationRepository
                .existsByJobJobIdAndJobSeekerJobSeekerId(request.getJobId(), request.getJobseekerId())){
            throw new JobApplicationDuplicationException("Cannot Apply For Same job once applied");
        }

        JobApplication jobApplication = jobApplicationMapper.toEntity(request, job,jobSeeker);
        jobApplication.setStatus(ApplicationStatus.APPLIED);
        JobApplication newJobApplication = jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toResponseDTO(newJobApplication);
    }
    public JobApplicationResponse getApplicationById(Integer id){
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                                                             .orElseThrow(() -> new JobApplicationNotFoundException("Job Application Not Found"));
        return jobApplicationMapper.toResponseDTO(jobApplication);
    }
    public List<JobApplicationSummaryResponse> getApplications(Integer id){
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobSeekerJobSeekerId(id);
        return jobApplications.stream().map(jobApplicationMapper::toSummaryResponseDTO).collect(Collectors.toList());
    }
    @Transactional
    public JobApplicationResponse withdrawApplication(Integer applicationId){
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                                                                        .orElseThrow(() -> new JobApplicationNotFoundException("Job Application Not Found"));
        ApplicationStatus status = jobApplication.getStatus();
        if(status.equals(ApplicationStatus.SELECTED)||status.equals(ApplicationStatus.REJECTED)||status.equals(ApplicationStatus.WITHDRAWN))throw new JobApplicationStatusUnchangeableException("Cannot Withdraw Job Application");
        jobApplication.setStatus(ApplicationStatus.WITHDRAWN);
        JobApplication newApplication = jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toResponseDTO(newApplication);
    }

    @Transactional
    public JobApplicationResponse updateApplicationStatus(ApplicationStatusUpdateRequest request){
        JobApplication jobApplication = jobApplicationRepository.findById(request.getApplicationId())
                                                                .orElseThrow(() -> new JobApplicationNotFoundException("Job Application Not Found"));
        if(jobApplication.getStatus().equals(ApplicationStatus.SELECTED)
                ||jobApplication.getStatus().equals(ApplicationStatus.REJECTED)
                ||jobApplication.getStatus().equals(ApplicationStatus.WITHDRAWN))throw new JobApplicationStatusUnchangeableException("Job Application has reached its terminal status");
        if(!request.getStatus().canTransitionTo(jobApplication.getStatus()))
        {
            throw new JobApplicationStatusUnchangeableException("Job Status Cannot be Changed");
        }
        jobApplication.setStatus(request.getStatus());
        jobApplicationRepository.save(jobApplication);

        return jobApplicationMapper.toResponseDTO(jobApplication);
    }
    public Page<JobApplicationSummaryResponse> searchApplications(JobApplicationSearchRequest request,Integer page,Integer size,String sort,String sortDirection){
        Specification<JobApplication> specification = jobApplicationSpecification.buildJobApplicationSpecification(request);
        if (sortDirection==null ||sortDirection.isBlank())sortDirection="asc";
        if(!(sortDirection.equalsIgnoreCase("asc")||sortDirection.equalsIgnoreCase("desc")))throw new JobApplicationInvalidSortingException("Provide Valid Sorting format");
        List<String> sortOrder = List.of("applicationId",
                "appliedDate",
                "status",
                "interviewDate",
                "offerSalary");
        if(sort==null|| ! sortOrder.contains(sort))throw new JobApplicationInvalidSortingException("Invalid sort field");
        if (page==null||page<0)page=0;
        if(size==null||size<=0)size=10;
        Sort sortBy =sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable pageRequest = PageRequest.of(
                page,
                size,
                sortBy
        );
        return jobApplicationRepository.findAll(specification,pageRequest).map(jobApplicationMapper::toSummaryResponseDTO);
    }
}
