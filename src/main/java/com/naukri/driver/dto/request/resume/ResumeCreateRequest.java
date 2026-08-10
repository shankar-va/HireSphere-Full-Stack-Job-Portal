package com.naukri.driver.dto.request.resume;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeCreateRequest {
    @NotBlank
    private String summary;
    @NotBlank
    private String education;
    @NotNull
    private Double experience;
    @Singular("project")
    @NotNull
    private Map<String,String> projects;    //key:represents project title and value: represents project description or summary
}
