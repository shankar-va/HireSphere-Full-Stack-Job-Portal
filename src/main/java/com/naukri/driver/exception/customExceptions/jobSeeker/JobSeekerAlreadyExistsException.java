package com.naukri.driver.exception.customExceptions.jobSeeker;

public class JobSeekerAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JobSeekerAlreadyExistsException(String message) {
		super(message);
	}

}
