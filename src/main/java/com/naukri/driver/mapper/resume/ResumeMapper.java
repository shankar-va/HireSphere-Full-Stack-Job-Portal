package com.naukri.driver.mapper.resume;

import com.naukri.driver.dto.request.resume.ResumeCreateRequest;
import com.naukri.driver.dto.request.resume.ResumeUpdateRequest;
import com.naukri.driver.dto.response.resume.ResumeResponse;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.model.entity.Resume;
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

    @Mapping(
            target = "jobSeeker",
            source = "jobSeeker"
    )
    Resume toEntity(
            ResumeCreateRequest request,
            JobSeeker jobSeeker
    );

    Resume updateResume(
            ResumeUpdateRequest request,
            @MappingTarget Resume resume
    );

    @Mapping(
            source = "jobSeeker.jobSeekerId",
            target = "jobSeekerId"
    )
    ResumeResponse toResponseDTO(Resume resume);
}