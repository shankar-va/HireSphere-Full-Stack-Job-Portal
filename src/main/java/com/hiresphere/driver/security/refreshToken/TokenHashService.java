package com.hiresphere.driver.security.refreshToken;


import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import java.util.HexFormat;


@Component

public class TokenHashService {


    public String hash(String rawToken) {


        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(rawToken.getBytes(StandardCharsets.UTF_8));

            return HexFormat.of()

                            .formatHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);

        }

    }


}

