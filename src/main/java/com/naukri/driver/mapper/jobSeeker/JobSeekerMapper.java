package com.naukri.driver.mapper.jobSeeker;

import com.naukri.driver.dto.request.jobseeker.JobSeekerRegistrationRequest;
import com.naukri.driver.dto.request.jobseeker.JobSeekerUpdateRequest;
import com.naukri.driver.dto.response.jobseeker.JobSeekerResponse;
import com.naukri.driver.dto.response.jobseeker.JobSeekerSummaryResponse;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.model.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobSeekerMapper {

    // 1. REPLACED @MappingTarget with standard parameter mapping
    // 2. Bridges case difference: request.headline -> entity.headLine
    // 3. Links the user entity directly to the jobSeeker.user property
    @Mapping(source = "request.headLine", target = "headLine")
    @Mapping(source = "user", target = "user")
    @Mapping(target = "jobSeekerId", ignore = true)
    @Mapping(target = "resume", ignore = true)
    @Mapping(target = "jobApplications", ignore = true)
    JobSeeker toEntity(JobSeekerRegistrationRequest request, User user);

    @Mapping(source = "headLine", target = "headLine")
    JobSeeker jobSeekerUpdateRequest(JobSeekerUpdateRequest request, @MappingTarget JobSeeker jobSeeker);

    // Bridges entity.jobSeekerId -> DTO.jobseekerId casing mismatch
    JobSeekerResponse toResponseDTO(JobSeeker jobSeeker);

    // Bridges entity.jobSeekerId -> DTO.jobseekerId casing mismatch
    JobSeekerSummaryResponse toResponseDTOSummary(JobSeeker jobSeeker);
}
