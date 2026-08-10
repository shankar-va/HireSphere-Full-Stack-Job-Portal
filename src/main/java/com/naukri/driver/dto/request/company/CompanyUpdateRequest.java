package com.naukri.driver.dto.request.company;

import com.naukri.driver.validation.interfaces.ValidatePhno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyUpdateRequest {
	@NotNull
	Integer companyId;
	@Size(min=1)
	String companyName;
	@Email
	String email;
	@ValidatePhno
	String phoneNumber;

}
