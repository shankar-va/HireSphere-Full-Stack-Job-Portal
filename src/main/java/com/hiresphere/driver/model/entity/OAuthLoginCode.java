package com.hiresphere.driver.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "oauth_login_codes", indexes = {@Index(name = "idx_oauth_login_code", columnList = "code_hash", unique = true)})
public class OAuthLoginCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_id")
    @SequenceGenerator(name = "code_id", sequenceName = "seq_code_id")
    private Long id;

    @Column(name = "code_hash", nullable = false, unique = true)
    private String codeHash;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean consumed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
