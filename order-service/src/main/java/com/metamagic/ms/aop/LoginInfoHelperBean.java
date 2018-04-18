package com.metamagic.ms.aop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author sagar
 * 
 * THIS BEAN ADDED USERID
 */
@Component
@Scope(value = "request")
public class LoginInfoHelperBean {

	private String userId;
	
	void setProperty(String userId){
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
