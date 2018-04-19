package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author sagar
 * THIS CLASS IS USED FOR CREATE CART
 */
public final class CreateCartCommand {

	@TargetAggregateIdentifier
	private final String cartId;

	private final String customerId;

	public CreateCartCommand(String cartId, String customerId) {
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
		return "CreateCartCommand [cartId=" + cartId + ", customerId=" + customerId + "]";
	}

}
