package com.naukri.driver.exception.customExceptions.resume;

public class ResumeAlreadyExistsException extends RuntimeException{
    public ResumeAlreadyExistsException(String message) {
        super(message);
    }
}
