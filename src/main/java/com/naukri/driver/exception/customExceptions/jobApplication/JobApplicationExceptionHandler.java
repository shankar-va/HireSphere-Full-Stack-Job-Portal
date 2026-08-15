package com.naukri.driver.exception.customExceptions.jobApplication;

import com.naukri.driver.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobApplicationExceptionHandler {
    @ExceptionHandler(JobApplicationClosedException.class)
    public ResponseEntity<ErrorResponseDTO> jobApplicationClosed(JobApplicationClosedException exception, HttpServletRequest request){
        ErrorResponseDTO error = (ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                  .message(exception.getMessage())).path(request.getRequestURI())
                                                                                   .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(JobApplicationCrossedDeadlineException.class)
    public ResponseEntity<ErrorResponseDTO> jobApplicationDeadLineCrossed(JobApplicationCrossedDeadlineException exception, HttpServletRequest request){
        ErrorResponseDTO error = (ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                  .message(exception.getMessage())).path(request.getRequestURI())
                                                                                   .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(JobApplicationDuplicationException.class)
    public ResponseEntity<ErrorResponseDTO> jobApplicationDuplicate(JobApplicationDuplicationException exception, HttpServletRequest request){
        ErrorResponseDTO error = (ErrorResponseDTO.builder().status(HttpStatus.ALREADY_REPORTED.value())
                                                  .message(exception.getMessage())).path(request.getRequestURI())
                                                                                   .build();
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(error);
    }
    @ExceptionHandler(JobApplicationInvalidSortingException.class)
    public ResponseEntity<ErrorResponseDTO> jobApplicationInvalidSorting(JobApplicationInvalidSortingException exception, HttpServletRequest request){
        ErrorResponseDTO error = (ErrorResponseDTO.builder().status(HttpStatus.EXPECTATION_FAILED.value())
                                                  .message(exception.getMessage())).path(request.getRequestURI())
                                                                                   .build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);
    }
    @ExceptionHandler(JobApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> jobApplicationNotFound(JobApplicationNotFoundException exception, HttpServletRequest request){
        ErrorResponseDTO error = (ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                  .message(exception.getMessage())).path(request.getRequestURI())
                                                                                   .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(JobApplicationPastJoinDateException.class)
    public ResponseEntity<ErrorResponseDTO> jobApplicationPastJoinDate(JobApplicationPastJoinDateException exception, HttpServletRequest request){
        ErrorResponseDTO error = (ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                  .message(exception.getMessage())).path(request.getRequestURI())
                                                                                   .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
    @ExceptionHandler(JobApplicationStatusUnchangeableException.class)
    public ResponseEntity<ErrorResponseDTO> jobApplicationStatusUnchangeable(JobApplicationStatusUnchangeableException exception, HttpServletRequest request) {
        ErrorResponseDTO error = (ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                  .message(exception.getMessage())).path(request.getRequestURI())
                                                                                   .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
}
