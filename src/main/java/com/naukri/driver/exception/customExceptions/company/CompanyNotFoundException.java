package com.naukri.driver.exception.customExceptions.company;

public class CompanyNotFoundException extends RuntimeException{
	public CompanyNotFoundException(String message) {
		super(message);
	}	
}
