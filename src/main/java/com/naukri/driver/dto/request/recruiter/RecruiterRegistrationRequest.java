package com.naukri.driver.dto.request.recruiter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterRegistrationRequest {
    @NotBlank
    private String designation;
    @NotBlank
    private String domain;
    @NotBlank
    private String employeeCode;
    @NotNull
    private Double experience;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer companyId;
}
