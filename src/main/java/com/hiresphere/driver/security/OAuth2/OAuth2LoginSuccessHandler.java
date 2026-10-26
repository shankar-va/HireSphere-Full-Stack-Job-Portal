package com.hiresphere.driver.security.OAuth2;

import com.hiresphere.driver.model.entity.User;
import com.hiresphere.driver.service.impl.OAuthLoginCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    private final OAuthLoginCodeService oauthLoginCodeService;

    @Value("${app.frontend.oauth2.redirect-url}")
    private String frontendRedirectUrl;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        CustomOAuth2UserDetails oauthUser =
                (CustomOAuth2UserDetails) authentication.getPrincipal();

        User user = oauthUser.getUser();

        String code = oauthLoginCodeService.issue(user);

        String targetUrl = UriComponentsBuilder
                .fromUriString(frontendRedirectUrl)
                .queryParam("code", code)
                .build()
                .toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}