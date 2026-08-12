package com.naukri.driver.validation.impl;


import com.naukri.driver.validation.interfaces.ValidatePhno;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPhno implements ConstraintValidator<ValidatePhno, String> {
	private final String PHONE_NO_PATTERN="^(?:\\+91|91)?[-\\s]?[6-9]\\d{9}$";
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null)return true;
		if(value.isBlank())return false;
		return value.matches(PHONE_NO_PATTERN);
	}

}
