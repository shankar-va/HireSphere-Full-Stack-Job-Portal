package com.naukri.driver.dto.request.jobseeker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class JobSeekerRegistrationRequest {
    @NotBlank
    private String headline;
    @NotNull
    private Double experience;
    @NotNull
    private Double currentSalary;
    @NonNull
    private Double expectedSalary;
    @NotNull
    @Singular("location")
    private List<String> preferredLocation;
    @NotNull
    private String highestQualification;
    @NotNull
    private Boolean availableForHire;
}
