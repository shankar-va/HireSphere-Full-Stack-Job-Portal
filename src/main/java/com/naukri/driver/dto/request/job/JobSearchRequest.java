package com.naukri.driver.dto.request.job;

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
public class JobSearchRequest {
    @Positive
    Integer jobId;
    String title;
    String description;
    List<String> preferredLocation;
    List<String> employmentMode;
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
