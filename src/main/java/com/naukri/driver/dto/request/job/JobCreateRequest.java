package com.naukri.driver.dto.request.job;

import java.time.LocalDate;
import java.util.List;

import com.naukri.driver.enumaration.job.EmploymentMode;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCreateRequest {
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotNull
	private Integer companyId;
	@NotNull
	private Integer recruiterId;
	@NotBlank
	private List<EmploymentMode> employmentMode;
	@PositiveOrZero
	private Double experienceRequired;
	@NotNull
	@Future
	private LocalDate applicationDeadLine;
	@Singular("location")
	private List<String> locations;
	@NotNull
	@Positive
	private Integer vacancies;
	@PositiveOrZero
	private Double minimum_sal;
	@PositiveOrZero
	private Double maximum_sal;
}
