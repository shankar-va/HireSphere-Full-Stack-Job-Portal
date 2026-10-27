package com.hiresphere.driver.dto.request.job;


import com.hiresphere.driver.enumaration.job.EmploymentMode;

import jakarta.validation.constraints.*;

import lombok.*;


import java.time.LocalDate;

import java.util.List;


@Data

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class JobCreateRequest {


    @NotBlank

    private String title;


    @NotBlank

    private String description;


    @NotNull

    private Integer companyId;


    @NotNull

    private Integer recruiterId;


    @NotNull

    private List<EmploymentMode> employmentMode;


    @PositiveOrZero

    private Double minimumExperienceRequired;

    @PositiveOrZero

    private Double maximumExperienceRequired;


    @NotNull

    @Future

    private LocalDate applicationDeadLine;


    @Singular("location")

    private List<String> preferredLocations;


    @NotNull

    @Positive

    private Integer vacancies;


    @PositiveOrZero

    private Double minimum_sal;


    @PositiveOrZero

    private Double maximum_sal;


}

