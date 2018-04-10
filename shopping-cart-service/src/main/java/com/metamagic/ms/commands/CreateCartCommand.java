package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateCartCommand {
	
	@TargetAggregateIdentifier
	private String cartId;
	
	private String customerId;

	public CreateCartCommand(String cartId, String customerId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CreateCartCommand [cartId=" + cartId + ", customerId=" + customerId + "]";
	}
	
	
}
