package com.hiresphere.driver.dto.response.jobApplication;


import com.hiresphere.driver.enumaration.jobApplication.ApplicationStatus;

import lombok.Builder;

import lombok.Value;


import java.time.LocalDate;


@Value

@Builder

public class JobApplicationResponse {


    Integer applicationId;


    LocalDate appliedDate;


    ApplicationStatus status;


    String coverLetter;


    LocalDate expectedJoinDate;


    LocalDate interviewDate;


    Double offerSalary;


}

