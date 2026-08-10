package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.jobApplication.ApplyForJobRequest;
import com.naukri.driver.dto.response.jobApplication.JobApplicationResponse;
import com.naukri.driver.mapper.jobApplication.JobApplicationMapper;
import com.naukri.driver.model.entity.Job;
import com.naukri.driver.model.entity.JobApplication;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.repository.JobApplicationRepository;
import com.naukri.driver.repository.JobRepository;
import com.naukri.driver.repository.JobSeekerRepository;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {
    private final JobRepository jobRepository;
    private final JobApplicationMapper jobApplicationMapper;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public JobApplicationService(JobRepository jobRepository, JobApplicationMapper jobApplicationMapper, JobApplicationRepository jobApplicationRepository, JobSeekerRepository jobSeekerRepository) {
        this.jobRepository = jobRepository;
        this.jobApplicationMapper = jobApplicationMapper;
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }

    public JobApplicationResponse register(ApplyForJobRequest request){
        Job job = jobRepository.findById(request.getJobId())
                                        .orElseThrow(() -> new RuntimeException("Invalid jobID"));
        JobSeeker jobSeeker = jobSeekerRepository.findById(request.getJobSeekerId())
                                                          .orElseThrow(() -> new RuntimeException("Invalid JobSeekerId"));
        JobApplication jobApplication = jobApplicationMapper.toEntity(request, job,jobSeeker);
        JobApplication newJobApplication = jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toResponseDTO(newJobApplication);
    }
}
