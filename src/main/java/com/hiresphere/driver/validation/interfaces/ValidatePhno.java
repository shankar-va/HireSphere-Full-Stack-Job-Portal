package com.hiresphere.driver.validation.interfaces;


import com.hiresphere.driver.validation.impl.ValidPhno;

import jakarta.validation.Constraint;

import jakarta.validation.Payload;


import java.lang.annotation.*;


@Documented

@Constraint(validatedBy = ValidPhno.class)

@Target({ElementType.FIELD, ElementType.PARAMETER})

@Retention(RetentionPolicy.RUNTIME)

public @interface ValidatePhno {


    String message() default "Mobile Number Validation Unsuccessful";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};


}

