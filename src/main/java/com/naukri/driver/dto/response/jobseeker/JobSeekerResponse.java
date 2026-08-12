package com.naukri.driver.dto.response.jobseeker;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class JobSeekerResponse {
    Integer jobSeekerId;
    String headLine;
    Double experience;
    Double currentSalary;
    Double expectedSalary;
    List<String> preferredLocation;
    String highestQualification;
    Boolean availableForHire;
}
