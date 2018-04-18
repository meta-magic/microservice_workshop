package com.metamagic.ms.events;

import java.io.Serializable;
import java.util.Date;

import com.metamagic.ms.aggregate.Items;

public final class ItemRemovedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5057743903662251460L;

	private final String cartId;

	private final String customerId;

	private final Items items;

	private final Date time;

	public ItemRemovedEvent(String cartId, String customerId, Items items) {
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
