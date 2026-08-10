package com.naukri.driver.exception.customExceptions.recruiter;

public class RecruiterUserExists extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecruiterUserExists(String message) {
		super(message);
	}

}
