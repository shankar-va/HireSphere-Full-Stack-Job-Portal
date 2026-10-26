package com.hiresphere.driver.security;


import com.hiresphere.driver.security.OAuth2.CustomOAuth2UserService;
import com.hiresphere.driver.security.OAuth2.OAuth2LoginSuccessHandler;
import com.hiresphere.driver.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration

@EnableWebSecurity

@EnableMethodSecurity

@RequiredArgsConstructor

public class SecurityConfig {


    private final CustomUserDetailsService service;


    private final CustomAccessDenierHandler accessDenierHandler;


    private final CustomAuthenticationEntryPoint authenticationEntryPoint;


    private final JwtFilter jwtFilter;


    private final CustomOAuth2UserService customOAuth2UserService;

    private final OAuth2LoginSuccessHandler loginSuccessHandler;

    private final AuthConfig authConfig;

    @Value("${app.cors.allowed-origins:http://localhost:5173}")
    private String allowedOrigins;

    @Bean

    public AuthenticationProvider authenticationProvider() {


        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(service);

        provider.setPasswordEncoder(authConfig.passwordEncoder());

        return provider;

    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Explicitly allow your frontend origin (Wildcards '*' cannot be used with allowCredentials)
        configuration.setAllowedOrigins(Arrays.stream(allowedOrigins.split(",")).map(String::trim).filter(origin->!origin.isBlank()).toList());

        // Allow standard HTTP methods required by your API
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Allow standard HTTP headers (or List.of("*") to allow all incoming headers)
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Cache-Control"));

        // CRITICAL: Explicitly allow cross-origin cookie transmission
        configuration.setAllowCredentials(true);

        // Apply this configuration block to all incoming API endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        return http.csrf(AbstractHttpConfigurer::disable)

                   .authenticationProvider(authenticationProvider())

                   .exceptionHandling(exception -> exception.accessDeniedHandler(accessDenierHandler)

                                                            .authenticationEntryPoint(authenticationEntryPoint))

                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                   .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/swagger-ui/**", "/v3/**")

                                                      .permitAll()

                                                      .anyRequest()

                                                      .authenticated())
                   .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                                                .successHandler(loginSuccessHandler))
                   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                   .cors(Customizer.withDefaults())

                   .build();

    }


}

