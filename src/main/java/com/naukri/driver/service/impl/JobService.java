package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.job.JobCreateRequest;
import com.naukri.driver.dto.response.job.JobResponse;
import com.naukri.driver.mapper.job.JobMapper;
import com.naukri.driver.model.entity.Company;
import com.naukri.driver.model.entity.Job;
import com.naukri.driver.model.entity.Recruiter;
import com.naukri.driver.repository.CompanyRepository;
import com.naukri.driver.repository.JobRepository;
import com.naukri.driver.repository.RecruiterRepository;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    CompanyRepository companyRepository;
    RecruiterRepository recruiterRepository;
    JobRepository jobRepository;
    JobMapper jobMapper;

    public JobService(CompanyRepository companyRepository, RecruiterRepository recruiterRepository, JobRepository jobRepository, JobMapper jobMapper) {
        this.companyRepository = companyRepository;
        this.recruiterRepository = recruiterRepository;
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public JobResponse createJob(JobCreateRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                                           .orElseThrow(() -> new RuntimeException("Invalid companyID"));
        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
                                                 .orElseThrow(() -> new RuntimeException("Invalid recruiterID"));
        Job jobEntity = jobMapper.toEntity(request, company, recruiter);
        Job newJob = jobRepository.save(jobEntity);
        return jobMapper.toResponseDTO(newJob);
    }
}
