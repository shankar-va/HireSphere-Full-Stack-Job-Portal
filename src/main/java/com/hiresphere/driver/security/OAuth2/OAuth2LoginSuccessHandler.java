package com.hiresphere.driver.security.OAuth2;

import com.hiresphere.driver.dto.request.auth.GeneratedRefreshToken;
import com.hiresphere.driver.model.entity.User;
import com.hiresphere.driver.security.jwt.JwtService;
import com.hiresphere.driver.security.refreshToken.RefreshTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    @Value("${app.frontend.oauth2.redirect-url}")
    private String frontendRedirectUrl;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2UserDetails oAuth2User = (CustomOAuth2UserDetails) authentication.getPrincipal();
        User user = oAuth2User.getUser();
        String accessToken = jwtService.generateAccessToken(oAuth2User);
        GeneratedRefreshToken refreshToken = refreshTokenService.createFamily(user);
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token", refreshToken.rawtoken())
                                                      .path("/auth")
                                                      .httpOnly(true)
                                                      .secure(true)
                                                      .sameSite("Strict")
                                                      .maxAge(Duration.ofDays(7))
                                                      .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        String targetUrl = UriComponentsBuilder.fromHttpUrl(frontendRedirectUrl)
                                               .queryParam("token", accessToken)
                                               .build()
                                               .toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

}
