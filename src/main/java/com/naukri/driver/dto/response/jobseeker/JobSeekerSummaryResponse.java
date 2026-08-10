package com.naukri.driver.dto.response.jobseeker;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JobSeekerSummaryResponse {
    Integer jobseekerId;
    String headline;
    Double experience;
    String highestQualification;
    Boolean availableForHire;

}
