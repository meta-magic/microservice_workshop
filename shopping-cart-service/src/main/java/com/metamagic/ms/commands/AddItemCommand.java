package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddItemCommand {
	

	@TargetAggregateIdentifier
	private String cartId;
	
	private String itemId;

	private String name;
	
	private int quantity;
	
	private Double price;
	
	public AddItemCommand(){
		
	}

	public AddItemCommand(String cartId, String itemId, String name, int quantity, Double price) {
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

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	@Override
	public String toString() {
		return "AddItemCommand [cartId=" + cartId + ", itemId=" + itemId + ", name=" + name + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}

}
