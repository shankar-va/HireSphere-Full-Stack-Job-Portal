package com.naukri.driver.mapper.job;

import com.naukri.driver.dto.request.job.JobCreateRequest;
import com.naukri.driver.dto.request.job.JobUpdateRequest;
import com.naukri.driver.dto.response.job.JobResponse;
import com.naukri.driver.dto.response.job.JobSummaryResponse;
import com.naukri.driver.model.entity.Company;
import com.naukri.driver.model.entity.Job;
import com.naukri.driver.model.entity.Recruiter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobMapper {
    Job toEntity(JobCreateRequest request, Company company, Recruiter recruiter);
    Job toUpdateJob(JobUpdateRequest request, @MappingTarget Job job);
    JobResponse toResponseDTO(Job job);
    JobSummaryResponse toResponseDTOSummary(Job job);
}
