package com.naukri.driver.exception.customExceptions.company;

public class CompanyEmailExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyEmailExistsException(String message) {
		super(message);
	}

}
