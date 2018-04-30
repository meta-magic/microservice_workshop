package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author sagar
 *
 */
public final class EmptyCartCommand {

	@TargetAggregateIdentifier
	private final String cartId;

	private final String customerId;

	public EmptyCartCommand(String cartId, String customerId) {
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
		return "EmptyCartCommand [cartId=" + cartId + ", customerId=" + customerId + "]";
	}

}
