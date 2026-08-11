package com.naukri.driver.dto.request.jobseeker;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSeekerUpdateRequest {
	@NotNull
	Integer jobSeekerId;
	@Size(min = 1)
	String headLine;
	@PositiveOrZero
	Double experience;
	@PositiveOrZero
	Double currentSalary;
	@PositiveOrZero
	Double expectedSalary;
	@Singular("location")
	List<String> preferredLocation;
	@Size(min = 1)
	String highestQualification;
	Boolean availableForHire;
	@Positive
	Integer resumeId;
}
