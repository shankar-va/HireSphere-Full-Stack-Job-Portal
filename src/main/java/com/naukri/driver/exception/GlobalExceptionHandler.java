package com.naukri.driver.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> validationException(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String ,String > errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        ErrorResponseDTO error = ErrorResponseDTO.builder().timestamp(LocalDate.now())
                                                 .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                                 .message(ex.getFieldError().getDefaultMessage())
                                                 .path(request.getRequestURI())
                                                 .validationErrors(errors).build();
        return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> exception(MethodArgumentNotValidException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ErrorResponseDTO.builder().timestamp(LocalDate.now()).status(HttpStatus.SERVICE_UNAVAILABLE.value()).message("The requested resource not found").path(request.getRequestURI()).build());
    }
}
