package com.naukri.driver.dto.response.jobApplication;

import com.naukri.driver.enumaration.jobApplication.ApplicationStatus;
import lombok.Value;

import java.time.LocalDate;
@Value
public class JobApplicationSummaryResponse {
    Integer applicationId;
    LocalDate appliedDate;
    ApplicationStatus status;
    Double offerSalary;
}
