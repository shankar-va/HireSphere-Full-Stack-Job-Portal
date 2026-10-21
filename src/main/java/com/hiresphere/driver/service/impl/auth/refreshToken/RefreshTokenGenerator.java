package com.hiresphere.driver.service.impl.auth.refreshToken;


import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;


import java.security.SecureRandom;

import java.util.Base64;


@Component

@NoArgsConstructor

public class RefreshTokenGenerator {


    private final SecureRandom secureRandom = new SecureRandom();



    public String generate() {


        byte[] bytes = new byte[32];

        secureRandom.nextBytes(bytes);

        return Base64.getUrlEncoder()

                     .withoutPadding()

                     .encodeToString(bytes);

    }


}

