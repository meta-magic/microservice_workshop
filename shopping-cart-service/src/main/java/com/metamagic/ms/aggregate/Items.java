package com.metamagic.ms.aggregate;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.validation.CommonValidation;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

/**
 * @author sagar THIS IS AN AGGREGATE ROOT
 */
public class Items implements CommonValidation {

	private String itemId;

	private String name;

	private int quantity;

	private double price;

	public Items() {

	}

	public Items(String itemId, String name, int quantity, double price) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) throws IllegalArgumentCustomException {
		if (!this.isValid(itemId)) {
			throw new IllegalArgumentCustomException("Item id should not be null");
		} else {
			this.itemId = itemId;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalArgumentCustomException {
		if (!this.isValid(itemId)) {
			throw new IllegalArgumentCustomException("Name should not be null");
		} else {
			this.name = name;
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) throws IllegalArgumentCustomException {
		if (!this.isValid(quantity)) {
			throw new IllegalArgumentCustomException("Quantity should not be null");
		} else {
			this.quantity = quantity;
		}

	}

	public double getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		return this.itemId.hashCode();
	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("itemId", itemId);
			jsonObject.put("name", name);
			jsonObject.put("quantity", quantity);
			jsonObject.put("price", price);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
