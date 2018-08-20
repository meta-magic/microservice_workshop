package com.metamagic.ms.aspect;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author sagar
 *
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
