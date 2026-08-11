package com.naukri.driver.specification.jobApplication;

import com.naukri.driver.dto.request.jobApplication.JobApplicationSearchRequest;
import com.naukri.driver.model.entity.JobApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildJobApplicationSpecification {
    private final JobApplicationSpecification specification;
    public Specification<JobApplication> buildJobApplicationSpecification(JobApplicationSearchRequest request){
        return specification.byApplicationId(request.getApplicationId())
                            .and(specification.byAppliedDate(request.getAppliedDate()))
                            .and(specification.byJobId(request.getJobId()))
                            .and(specification.byJobSeekerId(request.getJobSeekerId()))
                            .and(specification.byStatus(request.getStatus()));
    }
}
