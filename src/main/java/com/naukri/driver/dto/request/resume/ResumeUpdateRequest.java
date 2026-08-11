package com.naukri.driver.dto.request.resume;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeUpdateRequest {

    @NotNull
    private Integer resumeId;

    private String summary;

    private String education;

    private Map<String, String> projects;

    @PositiveOrZero
    private Double experience;
}