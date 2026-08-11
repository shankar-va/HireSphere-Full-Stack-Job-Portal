package com.naukri.driver.specification.job;

import com.naukri.driver.dto.request.job.JobSearchRequest;
import com.naukri.driver.model.entity.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildJobSpecification {
    private final JobSpecification jobSpecification;
    public Specification<Job> buildJobSpecification(JobSearchRequest request){
        return jobSpecification.byJobId(request.getJobId()).and(
                                jobSpecification.byJobTitle(request.getTitle())
                                                .and(jobSpecification.byJobDescription(request.getDescription()))
                                                .and(jobSpecification.byEmploymentMode(request.getEmploymentMode())))
                        .and(jobSpecification.byPreferredLocation(request.getPreferredLocation()))
                        .and(jobSpecification.byExperienceRange(request.getMinimumExperienceRequired(),request.getMaximumExperienceRequired()))
                        .and(jobSpecification.bySalaryRange(request.getMinimum_sal(),request.getMaximum_sal())).and(jobSpecification.byDeadline()).and(jobSpecification.byOpenJobs())
                        .and(jobSpecification.byVacancies(request.getVacancies()))
                        .and(jobSpecification.byCompany(request.getCompanyId()))
                        .and(jobSpecification.byRecruiter(request.getRecruiterId()));
    }
}
