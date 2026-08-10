package com.naukri.driver.dto.response.user;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class UserResponse {
	Integer userId;
	String firstName;
	String lastName;
	String email;
	String phoneNumber;
	LocalDate createdAt;
	
}
