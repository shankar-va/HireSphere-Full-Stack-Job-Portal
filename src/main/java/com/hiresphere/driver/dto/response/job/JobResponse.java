package com.hiresphere.driver.dto.response.job;


import com.hiresphere.driver.enumaration.job.EmploymentMode;
import lombok.Builder;

import lombok.Value;


import java.time.LocalDate;
import java.util.List;


@Value

@Builder

public class JobResponse {


    Integer jobId;


    String title;


    String description;


    List<String> preferredLocations;


    Double minimum_sal;


    Double maximum_sal;


    Double experienceRequired;


    Integer companyId;


    String companyName;

    List<EmploymentMode> employmentMode;

    LocalDate applicationDeadLine;

    Integer vacancies;

    Boolean isClosed;

}

