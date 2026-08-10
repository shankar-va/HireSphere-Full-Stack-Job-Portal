package com.naukri.driver.dto.response.job;

import lombok.Builder;
import lombok.Value;

import java.util.List;


@Value
@Builder
public class JobResponse {
    Integer jobId;
    String title;
    String description;
    List<String> location;
    Double minimum_sal;
    Double maximum_sal;
    Double experienceRequired;
    Integer companyId;
    String companyName;

}
