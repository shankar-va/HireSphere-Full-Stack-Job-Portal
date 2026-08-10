package com.naukri.driver.exception.customExceptions.user;

public class InvalidUserDetailsException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserDetailsException(String message) {
        super(message);
    }
}
