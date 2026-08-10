package com.naukri.driver.mapper.jobSeeker;

import com.naukri.driver.dto.request.jobseeker.JobSeekerRegistrationRequest;
import com.naukri.driver.dto.response.jobseeker.JobSeekerResponse;
import com.naukri.driver.dto.response.jobseeker.JobSeekerSummaryResponse;
import com.naukri.driver.model.entity.JobSeeker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobSeekerMapper {
    JobSeeker toEntity(JobSeekerRegistrationRequest request);
    JobSeekerResponse toResponseDTO(JobSeeker jobSeeker);
    JobSeekerSummaryResponse toResponseDTOSummary(JobSeeker jobSeeker);
}
