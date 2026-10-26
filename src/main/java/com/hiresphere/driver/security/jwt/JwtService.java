package com.hiresphere.driver.security.jwt;


import io.jsonwebtoken.Claims;

import io.jsonwebtoken.JwtException;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;

import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;

import java.time.Instant;

import java.util.Date;

import java.util.List;


@Service

public class JwtService {


    private final SecretKey secretKey;


    private final String issuer;


    private final String audience;


    private final long expirationMs;


    public JwtService(@Value("${security.jwt.issuer}") String issuer,

                      @Value("${security.jwt.secret}") String secretKey,

                      @Value("${security.jwt.audience}") String audience,

                      @Value("${security.jwt.access-token-ms}") long expirationMs) {


        byte[] decode = Decoders.BASE64.decode(secretKey);

        this.secretKey = Keys.hmacShaKeyFor(decode);

        this.issuer = issuer;

        this.audience = audience;

        this.expirationMs = expirationMs;

    }


    public String generateAccessToken(UserDetails userDetails) {


        List<String> authorities = userDetails.getAuthorities()

                                              .stream()

                                              .map(GrantedAuthority::getAuthority)

                                              .toList();

        Instant now = Instant.now();

        return Jwts.builder()

                   .signWith(secretKey)

                   .subject(userDetails.getUsername())

                   .issuedAt(Date.from(now))

                   .expiration(Date.from(now.plusMillis(expirationMs)))

                   .claim("authorities", authorities)

                   .issuer(issuer)

                   .audience()

                   .add(audience)

                   .and()

                   .compact();

    }


    public Claims validateAndExtractClaim(String token) {


        return Jwts.parser()

                   .verifyWith(secretKey)

                   .requireIssuer(issuer)

                   .requireAudience(audience)

                   .build()

                   .parseSignedClaims(token)

                   .getPayload();

    }


    public String extractEmail(String token) {


        return validateAndExtractClaim(token).getSubject();

    }


    public List<String> extractAuthorities(String token) {


        List<?> authorities = validateAndExtractClaim(token).get("authorities", List.class);

        if (authorities == null) {

            return List.of();

        }

        return authorities.stream()

                          .filter(String.class::isInstance)

                          .map(String.class::cast)

                          .toList();

    }


    public boolean isValidToken(String token) {


        try {

            Claims claims = validateAndExtractClaim(token);

            return claims.getSubject() != null && claims.getExpiration() != null && claims.getExpiration()

                                                                                          .after(new Date());

        } catch (JwtException | IllegalArgumentException exception) {

            return false;

        }

    }


}

