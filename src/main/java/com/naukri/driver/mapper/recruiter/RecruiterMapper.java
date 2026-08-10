package com.naukri.driver.mapper.recruiter;

import com.naukri.driver.dto.request.recruiter.RecruiterRegistrationRequest;
import com.naukri.driver.dto.request.recruiter.RecruiterUpdateRequest;
import com.naukri.driver.dto.response.recruiter.RecruiterResponse;
import com.naukri.driver.dto.response.recruiter.RecruiterSummaryResponse;
import com.naukri.driver.model.entity.Company;
import com.naukri.driver.model.entity.Recruiter;
import com.naukri.driver.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecruiterMapper {
    Recruiter toEntity(RecruiterRegistrationRequest request, User user, Company company);
    
    Recruiter toUpdateRecruiter(RecruiterUpdateRequest request,@MappingTarget Recruiter recruiter);
    
    RecruiterResponse toResponseDTO(Recruiter recruiter);
    
    @Mapping(source = "user.firstName", target = "recruiterName")
    RecruiterSummaryResponse toResponseDTOSummary(Recruiter recruiter);
}
