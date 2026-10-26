package com.hiresphere.driver.service.impl;

import com.hiresphere.driver.model.entity.OAuthLoginCode;
import com.hiresphere.driver.model.entity.User;
import com.hiresphere.driver.repository.OAuthLoginCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class OAuthLoginCodeService {

    private final OAuthLoginCodeRepository repository;

    private final SecureRandom secureRandom = new SecureRandom();

    @Transactional
    public String issue(User user) {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);

        String rawCode = Base64.getUrlEncoder()
                               .withoutPadding()
                               .encodeToString(bytes);

        OAuthLoginCode code = new OAuthLoginCode();
        code.setCodeHash(hash(rawCode));
        code.setUser(user);
        code.setExpiresAt(Instant.now().plus(60, ChronoUnit.SECONDS));
        code.setConsumed(false);

        repository.save(code);

        // Return the raw code only to the OAuth redirect handler.
        return rawCode;
    }

    @Transactional
    public String consumeAndGetEmail(String rawCode) {
        if (rawCode == null || rawCode.isBlank()) {
            throw new BadCredentialsException("Invalid OAuth login code");
        }

        String codeHash = hash(rawCode);

        int updated = repository.consumeIfValid(codeHash, Instant.now());

        if (updated != 1) {
            throw new BadCredentialsException(
                    "OAuth login code is invalid, expired, or already used"
            );
        }

        OAuthLoginCode code = repository.findByCodeHash(codeHash)
                                        .orElseThrow(() ->
                                                new BadCredentialsException("Invalid OAuth login code")
                                        );

        return code.getUser().getEmail();
    }

    private String hash(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest(
                    value.getBytes(StandardCharsets.UTF_8)
            );
            return HexFormat.of().formatHex(result);
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to hash OAuth code", ex);
        }
    }
}