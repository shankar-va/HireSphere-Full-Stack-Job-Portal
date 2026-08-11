package com.naukri.driver.dto.request.job;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
