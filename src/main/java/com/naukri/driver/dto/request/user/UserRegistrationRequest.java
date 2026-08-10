package com.naukri.driver.dto.request.user;

import com.naukri.driver.validation.interfaces.ValidatePassword;
import com.naukri.driver.validation.interfaces.ValidatePhno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationRequest {
	@NotBlank
	private String firstName;
	private String lastName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@ValidatePassword(message = "Password is either weak or Invalid")
	private String password;
	@NotBlank
	@ValidatePhno(message = "Please Provide valid Mobile Number")
	private String phoneNumber;
}
