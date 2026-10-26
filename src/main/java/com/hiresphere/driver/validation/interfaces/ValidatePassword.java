package com.hiresphere.driver.validation.interfaces;


import com.hiresphere.driver.validation.impl.ValidPassword;

import jakarta.validation.Constraint;

import jakarta.validation.Payload;


import java.lang.annotation.*;


@Documented

@Constraint(validatedBy = ValidPassword.class)

@Target({ElementType.FIELD, ElementType.PARAMETER})

@Retention(RetentionPolicy.RUNTIME)

public @interface ValidatePassword {


    String message() default "Password validation unsuccessful";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};


}

