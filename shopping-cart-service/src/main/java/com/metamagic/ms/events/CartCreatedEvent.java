package com.metamagic.ms.events;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartCreatedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3168408759513042290L;

	@JsonProperty
	private String cartId;
	
	@JsonProperty
	private String customerId;

	public CartCreatedEvent(String cartId, String customerId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
	}

	public String getCartId() {
		return cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	@Override
	public String toString() {
		return "CartCreatedEvent [cartId=" + cartId + ", customerId=" + customerId + "]";
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
