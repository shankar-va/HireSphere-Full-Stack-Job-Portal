package com.hiresphere.driver.dto.request.jobseeker;


import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;

import jakarta.validation.constraints.PositiveOrZero;

import jakarta.validation.constraints.Size;

import lombok.*;


import java.util.List;


@Data

@AllArgsConstructor

@NoArgsConstructor

@Builder

public class JobSeekerUpdateRequest {


    @NotNull

    Integer jobSeekerId;


    @Size(min = 1)

    String headLine;


    @PositiveOrZero

    Double experience;


    @PositiveOrZero

    Double currentSalary;


    @PositiveOrZero

    Double expectedSalary;


    @Singular("location")

    List<String> preferredLocation;


    @Size(min = 1)

    String highestQualification;


    Boolean availableForHire;


    @Positive

    Integer resumeId;


}

