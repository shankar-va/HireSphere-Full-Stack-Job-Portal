package com.naukri.driver.exception.customExceptions.resume;

import com.naukri.driver.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResumeExceptionHandler {
    @ExceptionHandler(ResumeAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> resumeAlreadyExists(ResumeAlreadyExistsException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resumeNotFound(ResumeNotFoundException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                                        .message(exception.getMessage())
                                                                        .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(ResumeOwnershipException.class)
    public ResponseEntity<ErrorResponseDTO> resumeOwnership(ResumeOwnershipException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.FORBIDDEN.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
