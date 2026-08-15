package com.naukri.driver.exception.customExceptions.jobSeeker;

import com.naukri.driver.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobSeekerExceptionHandler {
    @ExceptionHandler(JobSeekerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> jobSeekerAlreadyExists(JobSeekerAlreadyExistsException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.IM_USED.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.IM_USED).body(error);
    }
    @ExceptionHandler(JobSeekerInvalidSortingException.class)
    public ResponseEntity<ErrorResponseDTO> jobSeekerInvalidSorting(JobSeekerInvalidSortingException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
    @ExceptionHandler(JobSeekerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> jobSeekerNotFound(JobSeekerNotFoundException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
}
