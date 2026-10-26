package com.hiresphere.driver.controller;

import com.hiresphere.driver.dto.request.auth.GeneratedRefreshToken;
import com.hiresphere.driver.dto.request.auth.LoginResponse;
import com.hiresphere.driver.dto.request.auth.OAuthCodeExchangeRequest;
import com.hiresphere.driver.model.entity.User;
import com.hiresphere.driver.repository.UserRepository;
import com.hiresphere.driver.security.CustomUserDetails;
import com.hiresphere.driver.security.jwt.JwtService;
import com.hiresphere.driver.security.refreshToken.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth/oauth2")
@RequiredArgsConstructor
public class OAuth2ExchangeController {

    private final com.hiresphere.driver.service.impl.OAuthLoginCodeService oauthLoginCodeService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/exchange")
    public ResponseEntity<LoginResponse> exchange(
            @Valid @RequestBody OAuthCodeExchangeRequest request) {

        String email =
                oauthLoginCodeService.consumeAndGetEmail(request.code());

        User user = userRepository.findUserWithRolesAndPrivileges(email)
                                  .orElseThrow(() ->
                                          new BadCredentialsException("OAuth user not found")
                                  );

        CustomUserDetails principal = new CustomUserDetails(user);
        String accessToken = jwtService.generateAccessToken(principal);

        GeneratedRefreshToken refreshToken =
                refreshTokenService.createFamily(user);

        ResponseCookie cookie = ResponseCookie
                .from("refresh-token", refreshToken.rawtoken())
                .path("/auth")
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .maxAge(Duration.ofDays(7))
                .build();

        return ResponseEntity.ok()
                             .header(HttpHeaders.SET_COOKIE, cookie.toString())
                             .body(new LoginResponse(accessToken));
    }
}