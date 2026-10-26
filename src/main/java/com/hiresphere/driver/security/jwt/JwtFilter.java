package com.hiresphere.driver.security.jwt;


import io.jsonwebtoken.JwtException;

import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtService jwtService;


    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);

            return;

        }

        String token = header.substring(7);

        try {

            if (token != null && SecurityContextHolder.getContext()

                                                      .getAuthentication() == null) {

                if (jwtService.isValidToken(token)) {

                    String email = jwtService.extractEmail(token);

                    List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(token)

                                                                         .stream()

                                                                         .map(SimpleGrantedAuthority::new)

                                                                         .toList();

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(

                            email, null, authorities

                    );

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext()

                                         .setAuthentication(auth);

                }

            }

        } catch (JwtException | IllegalArgumentException exception) {

            SecurityContextHolder.clearContext();

        }

        filterChain.doFilter(request, response);

    }


}

