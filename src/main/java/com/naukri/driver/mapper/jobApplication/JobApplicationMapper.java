package com.naukri.driver.mapper.jobApplication;

import com.naukri.driver.dto.request.jobApplication.ApplyForJobRequest;
import com.naukri.driver.dto.response.jobApplication.JobApplicationResponse;
import com.naukri.driver.model.entity.Job;
import com.naukri.driver.model.entity.JobApplication;
import com.naukri.driver.model.entity.JobSeeker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    JobApplication toEntity(ApplyForJobRequest request, Job job, JobSeeker jobSeeker);
    JobApplicationResponse toResponseDTO(JobApplication jobApplication);
}
