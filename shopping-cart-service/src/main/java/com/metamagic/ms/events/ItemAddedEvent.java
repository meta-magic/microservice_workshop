package com.metamagic.ms.events;

import java.io.Serializable;
import java.util.Date;

import com.metamagic.ms.aggregate.Items;

/**
 * @author sagar
 * THIS EVENT IS USED FOR ITEM ADDED
 */
public final class ItemAddedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4435018607734487381L;

	private final String cartId;

	private final String customerId;

	private final Items items;

	private final Date time;

	public ItemAddedEvent(String cartId, String customerId, Items items) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.items = items;
		this.time = new Date();
	}

	public String getCartId() {
		return cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Items getItems() {
		return items;
	}

	public Date getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "ItemAddedEvent [cartId=" + cartId + ", customerId=" + customerId + ", items=" + items + ", time=" + time
				+ "]";
	}

}
