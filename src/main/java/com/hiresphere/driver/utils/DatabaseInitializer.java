package com.hiresphere.driver.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RolePrivilegeInitializer initializer;


    @Override
    public void run(String... args) throws Exception {

        initializer.initialize();
    }

}
