package com.naukri.driver.exception.customExceptions.jobApplication;

public class JobApplicationClosedException extends RuntimeException{
    public JobApplicationClosedException(String message) {
        super(message);
    }
}
