package com.naukri.driver.dto.response.jobseeker;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JobSeekerSummaryResponse {
    Integer jobSeekerId;
    String headLine;
    Double experience;
    String highestQualification;
    Boolean availableForHire;

}
