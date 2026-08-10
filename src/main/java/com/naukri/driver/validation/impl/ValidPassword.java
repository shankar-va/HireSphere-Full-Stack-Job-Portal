package com.naukri.driver.validation.impl;

import com.naukri.driver.validation.interfaces.ValidatePassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPassword implements ConstraintValidator<ValidatePassword, String> {
	private static String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$\r\n";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null||value.isBlank())return false;
		return value.matches(PASSWORD_PATTERN);
	}

}
