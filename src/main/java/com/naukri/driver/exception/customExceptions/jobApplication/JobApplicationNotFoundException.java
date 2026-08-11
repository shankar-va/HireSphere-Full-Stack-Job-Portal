package com.naukri.driver.exception.customExceptions.jobApplication;

public class JobApplicationNotFoundException extends RuntimeException{
    public JobApplicationNotFoundException(String message) {
        super(message);
    }
}
