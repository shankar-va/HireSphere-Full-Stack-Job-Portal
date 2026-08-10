package com.naukri.driver.dto.request.company;

import com.naukri.driver.validation.interfaces.ValidatePhno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegistrationRequest {
	@NotBlank
	private String companyName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@ValidatePhno(message = "Mobile number is Invalid")
	private String phoneNumber;
}
