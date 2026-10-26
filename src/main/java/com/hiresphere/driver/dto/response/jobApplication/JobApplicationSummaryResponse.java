package com.hiresphere.driver.dto.response.jobApplication;

import com.hiresphere.driver.enumaration.jobApplication.ApplicationStatus;

import java.time.LocalDate;

public record JobApplicationSummaryResponse(
        Integer applicationId,
        LocalDate appliedDate,
        ApplicationStatus status,
        Double offerSalary,
        Integer jobId,
        String jobTitle,
        Integer companyId,
        String companyName
) {

}

