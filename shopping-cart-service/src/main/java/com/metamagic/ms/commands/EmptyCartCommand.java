package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * @author sagar
 *
 */
public final class EmptyCartCommand {

	@TargetAggregateIdentifier
	private final String cartId;
	
	public EmptyCartCommand(String cartId){
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	@Override
	public String toString() {
		return "EmptyCartCommand [cartId=" + cartId + "]";
	}
	
	
}
