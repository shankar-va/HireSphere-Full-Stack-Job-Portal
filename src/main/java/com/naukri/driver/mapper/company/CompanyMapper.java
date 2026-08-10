package com.naukri.driver.mapper.company;

import com.naukri.driver.dto.request.company.CompanyRegistrationRequest;
import com.naukri.driver.dto.response.company.CompanyResponse;
import com.naukri.driver.dto.response.company.CompanySummaryResponse;
import com.naukri.driver.model.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CompanyRegistrationRequest request);
    CompanyResponse toResponseDTO(Company company);
    CompanySummaryResponse toResponseDTOSummary(Company company);
}
