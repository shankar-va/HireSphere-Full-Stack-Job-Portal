package com.hiresphere.driver.service.impl.auth;


import com.hiresphere.driver.dto.request.auth.AuthenticationResult;

import com.hiresphere.driver.dto.request.auth.GeneratedRefreshToken;

import com.hiresphere.driver.model.entity.User;

import com.hiresphere.driver.repository.UserRepository;

import com.hiresphere.driver.security.CustomUserDetails;

import com.hiresphere.driver.security.jwt.JwtService;

import com.hiresphere.driver.service.impl.auth.refreshToken.RefreshTokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@Service

@RequiredArgsConstructor

public class AuthenticationService {


    private final AuthenticationManager authenticationManager;


    private final UserRepository userRepository;


    private final JwtService jwtService;


    private final RefreshTokenService refreshTokenService;



    @Transactional

    public AuthenticationResult login(String email, String password) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticate = authenticationManager.authenticate(authToken);

        System.out.println("Authenticated user is found and verified!!!");

        UserDetails principal = (UserDetails) authenticate.getPrincipal();

        User user = userRepository.findUserWithRoles(principal.getUsername())

                                  .orElseThrow(() -> new IllegalStateException("Authenticated user was not found"));

        String access = jwtService.generateAccessToken(principal);

        GeneratedRefreshToken generated = refreshTokenService.createFamily(user);

        return new AuthenticationResult(access, generated.rawtoken());

    }



    @Transactional

    public AuthenticationResult refresh(String rawToken) {


        GeneratedRefreshToken replacement = refreshTokenService.rotate(rawToken);

        User user = replacement.refreshToken()

                               .getUser();

        UserDetails userDetails = new CustomUserDetails(user);

        String newAccessToken = jwtService.generateAccessToken(userDetails);

        return new AuthenticationResult(newAccessToken, replacement.rawtoken());

    }



    @Transactional

    public boolean logoutAllDevices(UserDetails userDetails) {


        User user = userRepository.findUserWithRoles(userDetails.getUsername())

                                  .orElseThrow(() -> new UsernameNotFoundException("User Not found"));

        refreshTokenService.revokeAllForUser(user.getUserId());

        return true;

    }



}

