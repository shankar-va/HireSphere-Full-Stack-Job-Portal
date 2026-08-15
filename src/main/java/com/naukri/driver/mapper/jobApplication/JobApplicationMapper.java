package com.naukri.driver.mapper.jobApplication;

import com.naukri.driver.dto.request.jobApplication.ApplyForJobRequest;
import com.naukri.driver.dto.response.jobApplication.JobApplicationResponse;
import com.naukri.driver.dto.response.jobApplication.JobApplicationSummaryResponse;
import com.naukri.driver.model.entity.Job;
import com.naukri.driver.model.entity.JobApplication;
import com.naukri.driver.model.entity.JobSeeker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface    JobApplicationMapper {
    @Mapping(source = "request.coverLetter", target = "coverLetter")
    @Mapping(source = "request.expectedJoinDate", target = "expectedJoinDate")
    @Mapping(source = "job", target = "job")
    @Mapping(source = "jobSeeker", target = "jobSeeker")
    JobApplication toEntity(ApplyForJobRequest request, Job job, JobSeeker jobSeeker);
    JobApplicationResponse toResponseDTO(JobApplication jobApplication);
    JobApplicationSummaryResponse toSummaryResponseDTO(JobApplication jobApplication);
}
