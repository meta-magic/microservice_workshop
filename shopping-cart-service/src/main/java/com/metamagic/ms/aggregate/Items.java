package com.metamagic.ms.aggregate;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

/**
 * @author sagar
 * THIS IS AN AGGREGATE ROOT
 */
public class Items {

	private String itemId;
	
	private String name;
	
	private int quantity;
	
	private double price;

	public Items(){
		
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
