package com.naukri.driver.dto.response.recruiter;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RecruiterResponse {
	Integer recruiterId;
	String designation;
	String domain;
	Double experience;
	Integer userId;
	Integer companyId;
}
