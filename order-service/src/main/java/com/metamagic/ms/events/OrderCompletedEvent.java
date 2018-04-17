package com.metamagic.ms.events;

import java.io.Serializable;

public class OrderCompletedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5726021038987464389L;

	private String userId;
	
	public OrderCompletedEvent() {
		
	}
	
	public OrderCompletedEvent(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
