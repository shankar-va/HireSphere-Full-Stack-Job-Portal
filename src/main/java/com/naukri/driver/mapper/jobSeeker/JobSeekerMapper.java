package com.naukri.driver.mapper.jobSeeker;

import com.naukri.driver.dto.request.jobseeker.JobSeekerRegistrationRequest;
import com.naukri.driver.dto.request.jobseeker.JobSeekerUpdateRequest;
import com.naukri.driver.dto.response.jobseeker.JobSeekerResponse;
import com.naukri.driver.dto.response.jobseeker.JobSeekerSummaryResponse;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.model.entity.User;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobSeekerMapper {
    JobSeeker toEntity(JobSeekerRegistrationRequest request,User user);
    JobSeeker jobSeekerUpdateRequest(JobSeekerUpdateRequest request,@MappingTarget JobSeeker jobSeeker);
    JobSeekerResponse toResponseDTO(JobSeeker jobSeeker);
    JobSeekerSummaryResponse toResponseDTOSummary(JobSeeker jobSeeker);
}
