package com.metamagic.ms.events;

import java.io.Serializable;
import java.util.Date;

import com.metamagic.ms.aggregate.Items;

public class ItemRemovedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5057743903662251460L;

	private String cartId;
	
	private String customerId;
	
	private Items items;
	
	private Date time;
	
	public ItemRemovedEvent(){
		this.time = new Date();
	}

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
	
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ItemAddedEvent [cartId=" + cartId + ", customerId=" + customerId + ", items=" + items + ", time=" + time
				+ "]";
	}

	 
	
}
