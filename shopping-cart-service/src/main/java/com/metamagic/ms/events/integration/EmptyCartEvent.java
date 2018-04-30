package com.metamagic.ms.events.integration;

import java.io.Serializable;

public class EmptyCartEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5726021038987464389L;

	private String userId;

	public EmptyCartEvent() {

	}

	public EmptyCartEvent(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
