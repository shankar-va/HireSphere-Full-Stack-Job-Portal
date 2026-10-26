package com.hiresphere.driver.exception.customExceptions.security;


import com.hiresphere.driver.exception.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice

public class RefreshTokenException {


    @ExceptionHandler(InvalidRefreshTokenException.class)

    public ResponseEntity<ErrorResponseDTO> invalidRefreshToken(InvalidRefreshTokenException exception, HttpServletRequest request) {


        ErrorResponseDTO error = ErrorResponseDTO.builder()

                                                 .status(HttpStatus.NOT_ACCEPTABLE.value())

                                                 .message(exception.getMessage())

                                                 .path(request.getRequestURI())

                                                 .build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)

                             .body(error);

    }


    @ExceptionHandler(RefreshTokenReuseException.class)

    public ResponseEntity<ErrorResponseDTO> refreshTokenReuse(RefreshTokenReuseException exception, HttpServletRequest request) {


        ErrorResponseDTO error = ErrorResponseDTO.builder()

                                                 .status(HttpStatus.IM_USED.value())

                                                 .message(exception.getMessage())

                                                 .build();

        return ResponseEntity.status(HttpStatus.IM_USED)

                             .body(error);

    }


}

