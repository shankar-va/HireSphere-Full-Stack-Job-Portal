package com.naukri.driver.dto.response.job;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JobSummaryResponse {
    Integer jobId;
    String title;
    String location;
    Integer companyId;
    String companyName;
}
