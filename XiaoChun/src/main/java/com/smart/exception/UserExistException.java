package com.smart.exception;

public class UserExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6855544510109804157L;

	public UserExistException(String errorMsg) {
		super(errorMsg);
	}
}
