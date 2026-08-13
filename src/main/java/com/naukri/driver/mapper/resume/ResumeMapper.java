package com.naukri.driver.mapper.resume;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.naukri.driver.dto.request.resume.ResumeCreateRequest;
import com.naukri.driver.dto.request.resume.ResumeUpdateRequest;
import com.naukri.driver.dto.response.resume.ResumeResponse;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.model.entity.Resume;


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
    @Mapping(source = "jobSeeker.jobSeekerId",target = "jobSeekerId")
    ResumeResponse toResponseDTO(Resume resume);
}