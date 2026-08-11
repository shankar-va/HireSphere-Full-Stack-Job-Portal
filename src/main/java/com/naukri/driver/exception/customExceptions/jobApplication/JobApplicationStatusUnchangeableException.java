package com.naukri.driver.exception.customExceptions.jobApplication;

public class JobApplicationStatusUnchangeableException extends RuntimeException{
    public JobApplicationStatusUnchangeableException(String message) {
        super(message);
    }
}
