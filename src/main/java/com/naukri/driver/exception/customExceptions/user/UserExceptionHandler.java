package com.naukri.driver.exception.customExceptions.user;

import com.naukri.driver.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(InvalidUserDetailsException.class)
    public ResponseEntity<ErrorResponseDTO> invalidUserDetails(InvalidUserDetailsException exception, HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> userNotFound(UserNotFoundException exception,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                 .message(exception.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
