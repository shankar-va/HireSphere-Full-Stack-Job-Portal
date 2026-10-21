package com.hiresphere.driver.model.entity;


import jakarta.persistence.*;

import lombok.*;


import java.time.Instant;

import java.util.UUID;


@Entity

@Getter

@Setter

@Builder

@NoArgsConstructor

@AllArgsConstructor

@Table(name = "refresh_token", indexes = @Index(name = "idx_refresh_family", columnList = "family_id"))

public class RefreshToken {


    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_id")

    @SequenceGenerator(name = "refresh_id", sequenceName = "seq_refresh_id", allocationSize = 1)

    @Column(name = "refresh_id")

    private long refreshId;


    @Column(name = "token_hash", nullable = false, updatable = false, unique = true, length = 64)

    private String tokenHash;


    @Column(name = "token_expiry", nullable = false, updatable = false)

    private Instant expiresAt;


    @Column(name = "token_revocation")

    private Instant revokedAt;


    @Column(name = "family_id", nullable = false)

    private UUID familyId;


    @Column(name = "replacement_hash", length = 64)

    private String replacedByHash;


    @Column(name = "created_at", nullable = false, updatable = false)

    private Instant createdAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    @JoinColumn(name = "user_info", nullable = false)

    private User user;



    public boolean isExpired() {


        return Instant.now()

                      .isAfter(expiresAt);

    }



    public boolean isRevoked() {


        return revokedAt != null;

    }



    public boolean isActive() {


        return !isExpired() && !isRevoked();

    }


}

