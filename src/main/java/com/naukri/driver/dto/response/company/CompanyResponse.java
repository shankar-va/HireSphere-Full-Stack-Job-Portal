package com.naukri.driver.dto.response.company;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompanyResponse {
	Integer companyId;
	String companyName;
	String email;
	String phoneNumber;
	Boolean isVerified;
}
