package com.naukri.driver.validation.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.naukri.driver.validation.impl.ValidPhno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidPhno.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePhno {
	String message() default "Mobile Number Validation Unsuccessful";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
