package com.hiresphere.driver.validation.impl;


import com.hiresphere.driver.validation.interfaces.ValidatePassword;


import jakarta.validation.ConstraintValidator;

import jakarta.validation.ConstraintValidatorContext;


public class ValidPassword implements ConstraintValidator<ValidatePassword, String> {


    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";



    @Override

    public boolean isValid(String value, ConstraintValidatorContext context) {


        if (value == null) return true;

        if (value.isBlank()) return false;

        return value.matches(PASSWORD_PATTERN);

    }


}

