package com.hiresphere.driver.controller;


import com.hiresphere.driver.dto.request.auth.AuthenticationResult;

import com.hiresphere.driver.dto.request.auth.LoginRequest;

import com.hiresphere.driver.dto.request.auth.LoginResponse;

import com.hiresphere.driver.dto.request.user.UserRegistrationRequest;

import com.hiresphere.driver.dto.response.user.UserResponse;

import com.hiresphere.driver.exception.customExceptions.security.InvalidRefreshTokenException;

import com.hiresphere.driver.service.impl.UserServiceImpl;

import com.hiresphere.driver.service.impl.AuthenticationService;

import com.hiresphere.driver.security.refreshToken.RefreshTokenService;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseCookie;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;


import java.time.Duration;


@RestController

@RequestMapping("/auth")

@RequiredArgsConstructor

public class AuthController {


    private static final String REFRESH_TOKEN = "refresh-token";

    private final AuthenticationService service;

    private final RefreshTokenService refreshTokenService;

    private final UserServiceImpl userService;


    @PostMapping("/register")

    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRegistrationRequest request) {


        return ResponseEntity.status(HttpStatus.CREATED)

                             .body(userService.register(request));

    }


    @PostMapping("/login")

    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {


        AuthenticationResult login = service.login(request.email(), request.password());

        setRefreshTokenService(response, login.refreshToken());

        return ResponseEntity.ok(new LoginResponse(login.accessToken()));

    }


    @PostMapping("/refresh")

    public ResponseEntity<LoginResponse> refresh(@CookieValue(name = "refresh-token", required = false) String rawToken, HttpServletResponse response) {


        if (rawToken == null || rawToken.isBlank()) {

            clearRefreshToken(response);

            throw new InvalidRefreshTokenException("Refresh token is missing");

        }


        AuthenticationResult refresh = service.refresh(rawToken);

        setRefreshTokenService(response, refresh.refreshToken());

        return ResponseEntity.ok(new LoginResponse(refresh.accessToken()));

    }


    @PostMapping("/logout")

    public ResponseEntity<Void> logout(@CookieValue(name = "refresh-token", required = false) String rawToken, HttpServletResponse response) {


        if (rawToken != null && !rawToken.isBlank()) {

            refreshTokenService.revoke(rawToken);

        }

        clearRefreshToken(response);

        return ResponseEntity.noContent()

                             .build();

    }


    @PostMapping("/logout-all")

    public ResponseEntity<Void> logoutAll(Authentication authentication, HttpServletResponse response) {


        UserDetails principal = (UserDetails) authentication.getPrincipal();


        service.logoutAllDevices(principal);

        clearRefreshToken(response);

        return ResponseEntity.noContent()

                             .build();

    }


    private void setRefreshTokenService(HttpServletResponse response, String refreshToken) {


        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, refreshToken)

                                              .httpOnly(true)

                                              .sameSite("Strict")

                                              .secure(true)

                                              .path("/auth")

                                              .maxAge(Duration.ofDays(7))

                                              .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

    }


    private void clearRefreshToken(HttpServletResponse response) {


        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, "")

                                              .sameSite("Strict")

                                              .httpOnly(true)

                                              .path("/auth")

                                              .secure(true)

                                              .maxAge(0)

                                              .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

    }


}

