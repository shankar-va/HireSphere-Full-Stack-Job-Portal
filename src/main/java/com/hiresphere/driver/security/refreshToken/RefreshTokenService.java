package com.hiresphere.driver.security.refreshToken;


import com.hiresphere.driver.dto.request.auth.GeneratedRefreshToken;

import com.hiresphere.driver.exception.customExceptions.security.InvalidRefreshTokenException;

import com.hiresphere.driver.exception.customExceptions.security.RefreshTokenReuseException;

import com.hiresphere.driver.model.entity.RefreshToken;

import com.hiresphere.driver.model.entity.User;

import com.hiresphere.driver.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;

import java.time.temporal.ChronoUnit;

import java.util.List;

import java.util.UUID;


@Service

@RequiredArgsConstructor

public class RefreshTokenService {


    private final long SESSION_DAYS = 7;


    private final RefreshTokenRepository repository;


    private final RefreshTokenGenerator tokenGenerator;


    private final TokenHashService hashService;


    @Transactional

    public GeneratedRefreshToken createFamily(User user) {


        Instant now = Instant.now();

        Instant deadline = now.plus(SESSION_DAYS, ChronoUnit.DAYS);

        UUID familyId = UUID.randomUUID();

        return create(user, now, deadline, familyId);

    }


    public GeneratedRefreshToken create(User user, Instant now, Instant deadline, UUID familyId) {


        String rawToken = tokenGenerator.generate();

        String hashedToken = hashService.hash(rawToken);

        RefreshToken refreshToken = RefreshToken.builder()

                                                .user(user)

                                                .tokenHash(hashedToken)

                                                .familyId(familyId)

                                                .expiresAt(deadline)

                                                .createdAt(now)

                                                .build();

        RefreshToken savedToken = repository.saveAndFlush(refreshToken);

        return new GeneratedRefreshToken(rawToken, refreshToken);

    }


    @Transactional(noRollbackFor = RefreshTokenReuseException.class)

    public GeneratedRefreshToken rotate(String rawToken) {


        String hashedToken = hashService.hash(rawToken);

        RefreshToken refreshToken = repository.findByTokenHash(hashedToken)

                                              .orElseThrow(() -> new InvalidRefreshTokenException("Invalid refresh token"));

        Instant now = Instant.now();

        if (refreshToken.isRevoked()) {

            repository.revokeFamily(now, refreshToken.getFamilyId());

            throw new RefreshTokenReuseException("Refresh token reuse detected");

        }

        if (refreshToken.isExpired()) {

            throw new InvalidRefreshTokenException("Refresh token expired");

        }

        User user = refreshToken.getUser();

        Instant deadline = refreshToken.getExpiresAt();

        UUID familyId = refreshToken.getFamilyId();

        GeneratedRefreshToken replacementToken = create(user, now, deadline, familyId);

        refreshToken.setRevokedAt(now);

        refreshToken.setReplacedByHash(replacementToken.refreshToken()

                                                       .getTokenHash());

        repository.saveAll(List.of(refreshToken, replacementToken.refreshToken()));

        return replacementToken;

    }


    @Transactional

    public void revoke(String rawtoken) {


        String hashedToken = hashService.hash(rawtoken);

        repository.findByTokenHash(hashedToken)

                  .ifPresent(token -> {

                      if (!token.isRevoked()) {

                          token.setRevokedAt(Instant.now());

                      }

                  });

    }


    @Transactional

    public void revokeAllForUser(Long userId) {


        repository.revokeAllForUser(Instant.now(), userId);

    }


}

