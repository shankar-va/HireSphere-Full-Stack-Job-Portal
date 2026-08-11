package com.naukri.driver.dto.request.jobseeker;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerSearchRequest {

    @Positive
    private Integer jobSeekerId;

    private String headLine;

    @PositiveOrZero
    private Double minimumExperience;

    @PositiveOrZero
    private Double maximumExperience;

    @PositiveOrZero
    private Double minimumCurrentSalary;

    @PositiveOrZero
    private Double maximumCurrentSalary;

    @PositiveOrZero
    private Double minimumExpectedSalary;

    @PositiveOrZero
    private Double maximumExpectedSalary;

    private List<String> preferredLocation;

    private String highestQualification;

    private Boolean availableForHire;
}