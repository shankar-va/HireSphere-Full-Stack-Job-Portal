package com.naukri.driver.dto.response.jobApplication;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class JobApplicationResponse {
    Integer applicationId;
    LocalDate appliedDate;
    String status;
    String coverLetter;
    LocalDate expectedJoinDate;
    LocalDate interviewDate;
    Double offerSalary;
}
