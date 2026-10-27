package com.hiresphere.driver.dto.request.job;


import com.hiresphere.driver.enumaration.job.EmploymentMode;

import jakarta.validation.constraints.*;

import lombok.*;


import java.time.LocalDate;

import java.util.List;

import java.util.Set;


@Data

@AllArgsConstructor

@NoArgsConstructor

@Builder

public class JobUpdateRequest {


    @NotNull

    Integer jobId;


    @Size(min = 1)

    String title;


    String description;


    @Singular("emp_mode")

    @Size(min = 1)

    Set<EmploymentMode> employmentMode;


    @PositiveOrZero

    Double minimum_sal;


    @PositiveOrZero

    Double maximum_sal;


    @PositiveOrZero

    private Double minimumExperienceRequired;


    @PositiveOrZero

    private Double maximumExperienceRequired;


    @Singular("location")

    List<String> preferredLocation;


    @Positive

    Integer vacancies;


    @Future

    LocalDate applicationDeadLine;


    Boolean isClosed;


}

