package com.naukri.driver.dto.response.job;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class JobSummaryResponse {
    Integer jobId;
    String title;
    List<String> preferredLocation;
    Integer companyId;
    String companyName;
}
