package com.naukri.driver.dto.request.jobseeker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class JobSeekerRegistrationRequest {
	@NotNull
	Integer userId;
    @NotBlank
    private String headline;
    @NotNull
    @PositiveOrZero
    private Double experience;
    @NotNull
    @PositiveOrZero
    private Double currentSalary;
    @NotNull
    @PositiveOrZero
    private Double expectedSalary;
    @Singular("location")
    private List<String> preferredLocation;
    @NotBlank
    private String highestQualification;
    @NotNull
    private Boolean availableForHire;
}
