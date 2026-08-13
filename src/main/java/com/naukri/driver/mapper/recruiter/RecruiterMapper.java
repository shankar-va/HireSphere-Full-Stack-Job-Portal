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

import java.lang.annotation.Target;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecruiterMapper {
    @Mapping(source = "user",target = "user")
    @Mapping(source = "company",target = "company")
    Recruiter toEntity(RecruiterRegistrationRequest request, User user, Company company);
    
    Recruiter toUpdateRecruiter(RecruiterUpdateRequest request,@MappingTarget Recruiter recruiter);

    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "company.companyId",target = "companyId")
    RecruiterResponse toResponseDTO(Recruiter recruiter);
    
    @Mapping(source = "user.firstName", target = "recruiterName")
    RecruiterSummaryResponse toResponseDTOSummary(Recruiter recruiter);
}
