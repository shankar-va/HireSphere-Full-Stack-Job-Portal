package com.naukri.driver.exception.customExceptions.recruiter;

public class RecruiterUserExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecruiterUserExistsException(String message) {
		super(message);
	}

}
