package com.naukri.driver.exception.customExceptions.jobApplication;

public class JobApplicationPastJoinDateException extends RuntimeException{
    public JobApplicationPastJoinDateException(String message) {
        super(message);
    }
}
