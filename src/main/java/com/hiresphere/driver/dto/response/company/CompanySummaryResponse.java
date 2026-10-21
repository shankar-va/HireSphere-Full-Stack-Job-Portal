package com.hiresphere.driver.dto.response.company;


import lombok.Builder;

import lombok.Value;


@Value

@Builder

public class CompanySummaryResponse {


    Integer companyId;


    String companyName;


}

