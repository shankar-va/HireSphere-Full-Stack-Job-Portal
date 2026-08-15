package com.naukri.driver.exception.customExceptions.job;

import com.naukri.driver.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobExceptionHandler {
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> jobNotFound(JobNotFoundException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(JobInvalidRecruiterToCompanyException.class)
    public ResponseEntity<ErrorResponseDTO> jobInvalidRecruiterToCompany(JobInvalidRecruiterToCompanyException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.SERVICE_UNAVAILABLE.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }
    @ExceptionHandler(JobInvalidSalaryRangeException.class)
    public ResponseEntity<ErrorResponseDTO> jobInvalidSalaryRange(JobInvalidSalaryRangeException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.EXPECTATION_FAILED.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);
    }
    @ExceptionHandler(JobInvalidSortingException.class)
    public ResponseEntity<ErrorResponseDTO> jobInvalidSorting(JobInvalidSortingException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                                                                        .status(HttpStatus.EXPECTATION_FAILED.value())
                                                                        .message(exception.getMessage())
                                                                        .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);
    }
}
