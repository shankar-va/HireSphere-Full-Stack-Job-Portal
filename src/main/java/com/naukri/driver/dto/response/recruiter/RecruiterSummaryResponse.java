package com.naukri.driver.dto.response.recruiter;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RecruiterSummaryResponse {
    Integer recruiterId;
    String recruiterName;
}
