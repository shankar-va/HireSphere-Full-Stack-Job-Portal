package com.naukri.driver.exception.customExceptions.job;

public class JobInvalidSalaryRangeException extends  RuntimeException
{
    public JobInvalidSalaryRangeException(String message) {
        super(message);
    }
}
