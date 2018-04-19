package com.metamagic.ms.bean;

import java.io.Serializable;

/**
 * @author sagar
 * RESPONSE BEAN
 */
public class ResponseBean implements Serializable
{

	private boolean success;
	private String message;
	private String code;
	private Object response;
	
	public ResponseBean(){
		super();
	}

	public ResponseBean(boolean success, String message, String code, Object response) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
		this.response = response;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	
}
