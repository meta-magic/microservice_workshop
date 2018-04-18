package com.metamagic.ms.entity;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author sagar
 *
 */
@PersistenceCapable
@EmbeddedOnly
@FetchGroup(name = "items", members = { @Persistent(name = "itemId"), @Persistent(name = "name"),
		@Persistent(name = "quantity"), @Persistent(name = "price") })
public class ItemDocument {
	private String itemId;

	private String name;

	private int quantity;

	private double price;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
