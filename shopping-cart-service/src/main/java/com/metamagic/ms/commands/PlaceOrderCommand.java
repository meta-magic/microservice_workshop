package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author sagar
 * 
 * THIS CLASS USED FOR PLACE ORDER
 */
public final class PlaceOrderCommand {
	
	@TargetAggregateIdentifier
	private final String cartId;
	
	private final String customerId;

	public PlaceOrderCommand(String cartId, String customerId) {
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
		return "PlaceOrderCommand [cartId=" + cartId + ", customerId=" + customerId + "]";
	}
	
	
}
