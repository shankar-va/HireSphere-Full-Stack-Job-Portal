package com.naukri.driver.dto.request.jobApplication;

import com.naukri.driver.enumaration.jobApplication.ApplicationStatus;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationSearchRequest {
    @Positive
    Integer applicationId;
    @Positive
    Integer jobId;
    @Positive
    Integer jobSeekerId;
    ApplicationStatus status;
    LocalDate appliedDate;
}
