package com.naukri.driver.exception.customExceptions.user;

public class InvalidUserDetailsException extends RuntimeException{
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
