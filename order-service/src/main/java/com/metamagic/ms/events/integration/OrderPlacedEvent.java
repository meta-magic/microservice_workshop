package com.metamagic.ms.events.integration;


import java.io.Serializable;
import java.util.Set;

import com.metamagic.ms.aggregate.Items;


/**
 * @author sagar
 * THIS IS ORDER PLACE EVENT USED TO SEND TO ORDER SERVICE
 */
public class OrderPlacedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885046483586380537L;

	private String cartId;
	
	private String customerId;
	
	private Set<Items> items;
	
	public OrderPlacedEvent(){
		
	}

	public OrderPlacedEvent(String cartId, String customerId, Set<Items> items) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.items = items;
	}

	public String getCartId() {
		return cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderPlacedEvent [cartId=" + cartId + ", customerId=" + customerId + ", items=" + items + "]";
	}
	
}
