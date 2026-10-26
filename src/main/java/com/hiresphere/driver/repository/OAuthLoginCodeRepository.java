package com.hiresphere.driver.repository;

import com.hiresphere.driver.model.entity.OAuthLoginCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface OAuthLoginCodeRepository extends JpaRepository<OAuthLoginCode, Long> {

    Optional<OAuthLoginCode> findByCodeHash(String codeHash);


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
            update OAuthLoginCode c
               set c.consumed = true
             where c.codeHash = :hash
               and c.consumed = false
               and c.expiresAt > :now
            """)
    int consumeIfValid(
            @Param("hash") String hash,
            @Param("now") Instant now
    );

}
