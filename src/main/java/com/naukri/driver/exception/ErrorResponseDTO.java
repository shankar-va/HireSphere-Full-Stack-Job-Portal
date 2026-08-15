package com.naukri.driver.exception;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    @Builder.Default
    private LocalDate timestamp=LocalDate.now();
    private Integer status;
    private String message;
    private String path;
    @Singular("validationError")
    private Map<String,String> validationErrors;
}
