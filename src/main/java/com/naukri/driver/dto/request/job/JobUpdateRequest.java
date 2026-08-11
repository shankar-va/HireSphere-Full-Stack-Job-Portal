package com.naukri.driver.dto.request.job;

import com.naukri.driver.enumaration.job.EmploymentMode;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

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
    List<EmploymentMode> employmentMode;
    @PositiveOrZero
    Double experienceRequired;
    @PositiveOrZero
    Double minimum_sal;
    @PositiveOrZero
    Double maximum_sal;
    @Singular("location")
    List<String> preferredLocation;
    @Positive
    Integer vacancies;
    @Future
    LocalDate applicationDeadLine;
    @BooleanFlag
    Boolean isClosed;
}
