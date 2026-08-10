package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.job.JobCreateRequest;
import com.naukri.driver.dto.request.job.JobSearchRequest;
import com.naukri.driver.dto.request.job.JobUpdateRequest;
import com.naukri.driver.dto.response.job.JobResponse;
import com.naukri.driver.dto.response.job.JobSummaryResponse;
import com.naukri.driver.exception.customExceptions.company.CompanyNotFoundException;
import com.naukri.driver.exception.customExceptions.job.JobInvalidRecruiterToCompanyException;
import com.naukri.driver.exception.customExceptions.job.JobInvalidSalaryRangeException;
import com.naukri.driver.exception.customExceptions.job.JobInvalidSortingException;
import com.naukri.driver.exception.customExceptions.job.JobNotFoundException;
import com.naukri.driver.exception.customExceptions.recruiter.RecruiterNotFound;
import com.naukri.driver.mapper.job.JobMapper;
import com.naukri.driver.model.entity.Company;
import com.naukri.driver.model.entity.Job;
import com.naukri.driver.model.entity.Recruiter;
import com.naukri.driver.repository.CompanyRepository;
import com.naukri.driver.repository.JobRepository;
import com.naukri.driver.repository.RecruiterRepository;
import com.naukri.driver.specification.JobSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final CompanyRepository companyRepository;
    private final RecruiterRepository recruiterRepository;
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobSpecification jobSpecification;

    public JobResponse createJob(JobCreateRequest request) {
        if(request.getCompanyId()==null){
            throw new CompanyNotFoundException("Company Not Found");
        }
        if(request.getRecruiterId()==null){
            throw new RecruiterNotFound("Recruiter Not found");
        }
        Company company = companyRepository.findById(request.getCompanyId())
                                           .orElseThrow(() -> new CompanyNotFoundException("Invalid companyID"));
        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
                                                 .orElseThrow(() -> new RecruiterNotFound("Invalid recruiterID"));
        Job jobEntity = jobMapper.toEntity(request, company, recruiter);
        if(request.getMinimum_sal()!=null && request.getMaximum_sal()!=null){
            if(request.getMinimum_sal()>request.getMaximum_sal()){
                throw new JobInvalidSalaryRangeException("Minimum Salary Cannot exceed Maximum Salary");
            }
        }

        if(!recruiter.getCompany().getCompanyId().equals(company.getCompanyId())){
            throw new JobInvalidRecruiterToCompanyException("Recruiter does not belong required Company");
        }
        Job newJob = jobRepository.save(jobEntity);
        return jobMapper.toResponseDTO(newJob);
    }
    public JobResponse getJobById(Integer id){
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job Not Found"));
        return jobMapper.toResponseDTO(job);
    }

    public JobResponse updateJob(JobUpdateRequest request){
        Job job = jobRepository.findById((request.getJobId()))
                                       .orElseThrow(() -> new JobNotFoundException("Job Not Found"));
        Double newMin = request.getMinimum_sal() != null
                ? request.getMinimum_sal()
                : job.getMinimum_sal();

        Double newMax = request.getMaximum_sal() != null
                ? request.getMaximum_sal()
                : job.getMaximum_sal();
        if(newMin!=null && newMax!=null){
            if(newMin>newMax)throw  new JobInvalidSalaryRangeException("Minimum Salary Cannot exceed Maximum Salary");
        }

        Job updatedJob = jobMapper.toUpdateJob(request, job);
        Job savedJob = jobRepository.save(updatedJob);
        return jobMapper.toResponseDTO(savedJob);
    }
    public void deleteJob(Integer jobId){
        if(!jobRepository.existsById(jobId))throw new JobNotFoundException("Job Not Found");
        jobRepository.deleteById(jobId);
    }
    public Page<JobSummaryResponse> searchJobs( Integer page, Integer size, String sort,String sortDirection, JobSearchRequest request){
        Specification<Job> specification = jobSpecification.byJobId(request.getJobId()).and(
                                                         jobSpecification.byJobTitle(request.getTitle())
                                                                         .and(jobSpecification.byJobDescription(request.getDescription()))
                                                                         .and(jobSpecification.byEmploymentMode(request.getEmploymentMode())))
                                                 .and(jobSpecification.byPreferredLocation(request.getPreferredLocation()))
                                                 .and(jobSpecification.byMinimumExperience(request.getMinimumExperienceRequired()))
                                                 .and(jobSpecification.byMaximumExperience(request.getMaximumExperienceRequired()))
                                                 .and(jobSpecification.byMinimumSalary(request.getMinimum_sal()))
                                                 .and(jobSpecification.byMaximumSalary(request.getMaximum_sal()))
                                                 .and(jobSpecification.byVacancies(request.getVacancies()))
                                                 .and(jobSpecification.byCompany(request.getCompanyId()))
                                                 .and(jobSpecification.byRecruiter(request.getRecruiterId()));
        Sort sortBy = Optional.of(sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sort).ascending() : Sort.by(sort).descending()).orElseThrow(()->new JobInvalidSortingException("Provide Valid Sorting format"));
        PageRequest pageRequest = PageRequest.of(page, size, sortBy);
        return jobRepository.findAll(specification,pageRequest).map(jobMapper::toResponseDTOSummary);

    }

    public JobSummaryResponse closeJob(Integer jobId){
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException("Job Not Found"));
        job.setIsClosed(true);
        Job newJob = jobRepository.save(job);
        return jobMapper.toResponseDTOSummary(newJob);
    }
}
