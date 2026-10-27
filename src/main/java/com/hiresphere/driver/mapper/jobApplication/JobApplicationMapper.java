package com.hiresphere.driver.mapper.jobApplication;


import com.hiresphere.driver.dto.request.jobApplication.ApplyForJobRequest;

import com.hiresphere.driver.dto.response.jobApplication.JobApplicationResponse;

import com.hiresphere.driver.dto.response.jobApplication.JobApplicationSummaryResponse;

import com.hiresphere.driver.model.entity.Job;

import com.hiresphere.driver.model.entity.JobApplication;

import com.hiresphere.driver.model.entity.JobSeeker;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface JobApplicationMapper {


    @Mapping(source = "request.coverLetter", target = "coverLetter")

    @Mapping(source = "request.expectedJoinDate", target = "expectedJoinDate")

    @Mapping(source = "job", target = "job")

    @Mapping(source = "jobSeeker", target = "jobSeeker")
    JobApplication toEntity(ApplyForJobRequest request, Job job, JobSeeker jobSeeker);


    JobApplicationResponse toResponseDTO(JobApplication jobApplication);

    @Mapping(source = "job.jobId", target = "jobId")
    @Mapping(source = "job.title", target = "jobTitle")
    @Mapping(source = "job.company.companyId", target = "companyId")
    @Mapping(source = "job.company.companyName", target = "companyName")
    JobApplicationSummaryResponse toSummaryResponseDTO(JobApplication jobApplication);


}

