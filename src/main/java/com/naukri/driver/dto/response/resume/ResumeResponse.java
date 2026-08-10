package com.naukri.driver.dto.response.resume;

import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Value
@Builder
public class ResumeResponse {
    Integer resumeId;
    String summary;
    String education;
    Map<String,String> projects;
}
