package com.hiresphere.driver.dto.request.job;


import com.hiresphere.driver.enumaration.job.EmploymentMode;

import jakarta.validation.constraints.Positive;

import jakarta.validation.constraints.PositiveOrZero;

import lombok.*;


import java.util.List;

import java.util.Set;


@Data

@AllArgsConstructor

@NoArgsConstructor

@Builder

public class JobSearchRequest {


    @Positive

    Integer jobId;


    String title;


    String description;


    @Singular("location")

    List<String> preferredLocation;


    @Singular("empMode")

    Set<EmploymentMode> employmentMode;


    @PositiveOrZero

    Double minimumExperienceRequired;


    @PositiveOrZero

    Double maximumExperienceRequired;


    @PositiveOrZero

    Integer vacancies;


    @PositiveOrZero

    Double minimum_sal;


    @PositiveOrZero

    Double maximum_sal;


    @Positive

    Integer companyId;


    @Positive

    Integer recruiterId;


}

