package com.metamagic.ms.events.integration;

import java.io.Serializable;
import java.util.Set;

import com.metamagic.ms.aggregate.Items;
/**
 * @author sagar
 * THIS EVENT IS USED FOR ORDE PLACED
 */
public final class OrderPlacedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885046483586380537L;

	private final String cartId;

	private final String userId;

	private final Set<Items> items;

	public OrderPlacedEvent(String cartId, String userId, Set<Items> items) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.items = items;
	}

	public String getCartId() {
		return cartId;
	}
	
	public String getUserId() {
		return userId;
	}

	public Set<Items> getItems() {
		return items;
	}

}
