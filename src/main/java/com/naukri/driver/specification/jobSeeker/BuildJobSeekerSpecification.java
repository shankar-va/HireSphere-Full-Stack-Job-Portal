package com.naukri.driver.specification.jobSeeker;

import com.naukri.driver.dto.request.jobseeker.JobSeekerSearchRequest;
import com.naukri.driver.model.entity.JobSeeker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildJobSeekerSpecification {

    private final JobSeekerSpecification jobSeekerSpecification;

    public Specification<JobSeeker> buildJobSeekerSpecification(
            JobSeekerSearchRequest request) {

        return Specification
                .where(
                        jobSeekerSpecification
                                .byJobSeekerId(request.getJobSeekerId())
                )
                .and(
                        jobSeekerSpecification
                                .byHeadline(request.getHeadLine())
                )
                .and(
                        jobSeekerSpecification
                                .byExperienceRange(
                                        request.getMinimumExperience(),
                                        request.getMaximumExperience()
                                )
                )
                .and(
                        jobSeekerSpecification
                                .byCurrentSalaryRange(
                                        request.getMinimumCurrentSalary(),
                                        request.getMaximumCurrentSalary()
                                )
                )
                .and(
                        jobSeekerSpecification
                                .byExpectedSalaryRange(
                                        request.getMinimumExpectedSalary(),
                                        request.getMaximumExpectedSalary()
                                )
                )
                .and(
                        jobSeekerSpecification
                                .byPreferredLocations(
                                        request.getPreferredLocation()
                                )
                )
                .and(
                        jobSeekerSpecification
                                .byHighestQualification(
                                        request.getHighestQualification()
                                )
                )
                .and(
                        jobSeekerSpecification
                                .byAvailability(
                                        request.getAvailableForHire()
                                )
                );
    }
}