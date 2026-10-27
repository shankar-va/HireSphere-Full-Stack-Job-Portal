package com.hiresphere.driver.repository;


import com.hiresphere.driver.model.entity.RefreshToken;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Lock;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import java.time.Instant;

import java.util.Optional;

import java.util.UUID;


@Repository

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)

    @Query("""
            
            SELECT rt FROM RefreshToken rt LEFT JOIN FETCH rt.user WHERE rt.tokenHash=:token""")
    Optional<RefreshToken> findByTokenHash(@Param("token") String

                                                   token);

    @Modifying
    @Query("""
            
            UPDATE RefreshToken rt SET rt.revokedAt=:now WHERE rt.familyId=:familyId AND rt.revokedAt IS NULL""")
    int revokeFamily(@Param("now") Instant now, @Param("familyId") UUID fmailyId);

    @Modifying
    @Query("""
            
            UPDATE RefreshToken rt SET rt.revokedAt=:now WHERE rt.user.userId=:userId AND rt.revokedAt IS NULL""")
    int revokeAllForUser(@Param("now") Instant now, @Param("userId") long userId);


}

