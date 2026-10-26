package com.hiresphere.driver.mapper.resume;


import com.hiresphere.driver.dto.request.resume.ResumeCreateRequest;

import com.hiresphere.driver.dto.request.resume.ResumeUpdateRequest;

import com.hiresphere.driver.dto.response.resume.ResumeResponse;

import com.hiresphere.driver.model.entity.JobSeeker;

import com.hiresphere.driver.model.entity.Resume;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

import org.mapstruct.MappingTarget;

import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(

        componentModel = "spring",

        nullValuePropertyMappingStrategy =

                NullValuePropertyMappingStrategy.IGNORE

)

public interface ResumeMapper {


    @Mapping(source = "request.experience", target = "experience")
    Resume toEntity(

            ResumeCreateRequest request,


            JobSeeker jobSeeker

    );


    Resume updateResume(

            ResumeUpdateRequest request,

            @MappingTarget Resume resume

    );


    @Mapping(source = "jobSeeker.jobSeekerId", target = "jobSeekerId")
    ResumeResponse toResponseDTO(Resume resume);


}
