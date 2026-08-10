package com.naukri.driver.dto.request.recruiter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruiterUpdateRequest {
	@NotNull
	Integer recruiterId;
	@Size(min=1)
	String designation;
	@Size(min = 1)
	String domain;
	String employeeCode;
	@PositiveOrZero
	Double experience;
	Boolean isActive;
}
