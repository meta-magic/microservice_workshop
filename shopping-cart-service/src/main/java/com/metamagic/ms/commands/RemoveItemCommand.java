package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public final class RemoveItemCommand {
	
	@TargetAggregateIdentifier
	private final String cartId;
	
	private final String itemId;

	private final String name;
	
	private final int quantity;
	
	private final Double price;

	public RemoveItemCommand(String cartId, String itemId, String name, int quantity, Double price) {
		super();
		this.cartId = cartId;
		this.itemId = itemId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getCartId() {
		return cartId;
	}

	public String getItemId() {
		return itemId;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "RemoveItemCommand [cartId=" + cartId + ", itemId=" + itemId + ", name=" + name + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
	
}
