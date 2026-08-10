package com.naukri.driver.dto.request.jobApplication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyForJobRequest {
    @NotNull
    Integer jobId;
    @NotNull
    Integer JobSeekerId;
    @NotBlank
    private String coverLetter;
    private LocalDate expectedJoinDate;
}
