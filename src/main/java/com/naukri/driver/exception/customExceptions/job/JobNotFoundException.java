package com.naukri.driver.exception.customExceptions.job;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(String message) {
        super(message);
    }
}
