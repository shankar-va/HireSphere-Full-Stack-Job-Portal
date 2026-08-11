package com.naukri.driver.exception.customExceptions.jobApplication;

public class JobApplicationCrossedDeadlineException extends RuntimeException{
    public JobApplicationCrossedDeadlineException(String message) {
        super(message);
    }
}
