package com.metamagic.ms.exception;

/**
 * @author sagar
 * 
 * THIS EXCEPTION CLASSS IS USED FOR ENTITY FIELDS VALIDATION
 */
public class IllegalArgumentCustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalArgumentCustomException(String message) {
		super(message);
	}
}
