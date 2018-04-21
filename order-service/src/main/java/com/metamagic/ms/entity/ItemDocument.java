package com.metamagic.ms.entity;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.validation.CommonValidation;

/**
 * @author sagar THIS CLASS IS USED FOR ITEM OF ORDER
 */
@PersistenceCapable
@EmbeddedOnly
@FetchGroup(name = "items", members = { @Persistent(name = "itemId"), @Persistent(name = "name"),
		@Persistent(name = "quantity"), @Persistent(name = "price") })
public class ItemDocument implements CommonValidation {
	private String itemId;

	private String name;

	private int quantity;

	private double price;

	public ItemDocument(String itemId, String name, int quantity, double price) throws IllegalArgumentCustomException {
		super();
		this.setItemId(itemId);
		this.setName(name);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	public String getItemId() {
		return itemId;
	}

	private void setItemId(String itemId) throws IllegalArgumentCustomException {
		if (!this.isValid(itemId)) {
			throw new IllegalArgumentCustomException("Item Id should not be null.");
		} else {
			this.itemId = itemId;
		}
	}

	public String getName() {
		return name;
	}

	private void setName(String name) throws IllegalArgumentCustomException {
		if (!this.isValid(name)) {
			throw new IllegalArgumentCustomException("Name should not be null.");
		} else {
			this.name = name;
		}
	}

	public int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) throws IllegalArgumentCustomException {
		if (!this.isValid(quantity)) {
			throw new IllegalArgumentCustomException("Quantity should not be null.");
		} else {
			this.quantity = quantity;
		}

	}

	public double getPrice() {
		return price;
	}

	private void setPrice(double price) throws IllegalArgumentCustomException {
		if (!this.isValid(price)) {
			throw new IllegalArgumentCustomException("Price should not be null.");
		} else {
			this.price = price;
		}

	}

}
