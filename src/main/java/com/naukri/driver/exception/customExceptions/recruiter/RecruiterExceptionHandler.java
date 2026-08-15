package com.naukri.driver.exception.customExceptions.recruiter;

import com.naukri.driver.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RecruiterExceptionHandler {
    @ExceptionHandler(RecruiterEmployeeCodeExistsException.class)
    public ResponseEntity<ErrorResponseDTO> recruiterEmployeeCodeExists(RecruiterEmployeeCodeExistsException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
    @ExceptionHandler(RecruiterEmployeeCodeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> recruiterEmployeeCodeMismatch(RecruiterEmployeeCodeMismatchException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.MULTI_STATUS.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(error);
    }
    @ExceptionHandler(RecruiterNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> recruiterNotFound(RecruiterNotFoundException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(RecruiterUserExistsException.class)
    public ResponseEntity<ErrorResponseDTO> recruiterUserExists(RecruiterUserExistsException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.ALREADY_REPORTED.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(error);
    }
}
