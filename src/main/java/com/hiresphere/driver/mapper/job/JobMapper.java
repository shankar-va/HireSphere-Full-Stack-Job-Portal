package com.hiresphere.driver.mapper.job;


import com.hiresphere.driver.dto.request.job.JobCreateRequest;

import com.hiresphere.driver.dto.request.job.JobUpdateRequest;

import com.hiresphere.driver.dto.response.job.JobResponse;

import com.hiresphere.driver.dto.response.job.JobSummaryResponse;

import com.hiresphere.driver.model.entity.Company;

import com.hiresphere.driver.model.entity.Job;

import com.hiresphere.driver.model.entity.Recruiter;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

import org.mapstruct.MappingTarget;

import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface JobMapper {


    @Mapping(target = "preferredLocations", expression = "java(request.getPreferredLocations() != null ? new java.util.ArrayList<>(request.getPreferredLocations()) : null)")

    @Mapping(source = "request.employmentMode", target = "employmentMode")

    @Mapping(source = "company", target = "company")

    @Mapping(source = "recruiter", target = "recruiter")
    Job toEntity(JobCreateRequest request, Company company, Recruiter recruiter);


    Job toUpdateJob(JobUpdateRequest request, @MappingTarget Job job);


    @Mapping(source = "company.companyId", target = "companyId")

    @Mapping(source = "company.companyName", target = "companyName")

    @Mapping(source = "preferredLocations", target = "preferredLocations")
    JobResponse toResponseDTO(Job job);


    @Mapping(source = "company.companyId", target = "companyId")

    @Mapping(source = "company.companyName", target = "companyName")

    @Mapping(source = "preferredLocations", target = "preferredLocations")
    JobSummaryResponse toResponseDTOSummary(Job job);


}

