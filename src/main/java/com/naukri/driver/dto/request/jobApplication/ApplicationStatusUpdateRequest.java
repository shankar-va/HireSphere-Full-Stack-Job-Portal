package com.naukri.driver.dto.request.jobApplication;

import com.naukri.driver.enumaration.jobApplication.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusUpdateRequest {
    @NotNull
    private Integer applicationId;
    @NotNull
    private ApplicationStatus status;
}
