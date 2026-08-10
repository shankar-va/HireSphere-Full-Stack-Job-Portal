package com.naukri.driver.mapper.company;

import com.naukri.driver.dto.request.company.CompanyRegistrationRequest;
import com.naukri.driver.dto.request.company.CompanyUpdateRequest;
import com.naukri.driver.dto.response.company.CompanyResponse;
import com.naukri.driver.dto.response.company.CompanySummaryResponse;
import com.naukri.driver.model.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {
    Company toEntity(CompanyRegistrationRequest request);
    Company toUpdateEntity( CompanyUpdateRequest request,@MappingTarget Company company);
    CompanyResponse toResponseDTO(Company company);
    CompanySummaryResponse toResponseDTOSummary(Company company);
}
