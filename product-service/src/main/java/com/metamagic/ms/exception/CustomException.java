package com.metamagic.ms.exception;

/**
 * @author sagar
 * 
 * THIS CUSTOME EXCEPTION CLASS USED FOR TO THROW CUSTOM EXCEPTION 
 */
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomException(String exception) {
		super(exception);
	}
}
