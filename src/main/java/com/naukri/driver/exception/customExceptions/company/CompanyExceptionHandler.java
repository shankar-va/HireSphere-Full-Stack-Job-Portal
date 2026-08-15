package com.naukri.driver.exception.customExceptions.company;

import com.naukri.driver.exception.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class CompanyExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> companyNotFound(CompanyNotFoundException ex, HttpServletRequest request){
        ErrorResponseDTO companyNotFound = ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND.value())
                                                                                   .message(ex.getMessage())
                                                                                   .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(companyNotFound);
    }
    @ExceptionHandler(CompanyEmailExistsException.class)
    public ResponseEntity<ErrorResponseDTO> companyEmailExists(CompanyEmailExistsException ex,HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                                        .message(ex.getMessage())
                                                                        .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
    @ExceptionHandler(CompanyPhoneNumberExistsException.class)
    public ResponseEntity<ErrorResponseDTO> companyPhoneNumberExists(CompanyPhoneNumberExistsException ex,HttpServletRequest request){
        ErrorResponseDTO phoneNumberAlreadyExists = ErrorResponseDTO.builder().status(HttpStatus.NOT_ACCEPTABLE.value())
                                                                    .message(ex.getMessage())
                                                                    .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(phoneNumberAlreadyExists);
    }
}
