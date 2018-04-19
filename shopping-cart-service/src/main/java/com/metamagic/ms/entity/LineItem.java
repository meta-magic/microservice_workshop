package com.metamagic.ms.entity;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author sagar
 * 
 * THIS LINE ITEM IS SUBDOCUMENTS OF USER CART
 */
@PersistenceCapable
@EmbeddedOnly
@FetchGroup(name = "lineItems", members = { @Persistent(name = "id"), @Persistent(name = "name"),
		@Persistent(name = "quantity"), @Persistent(name = "price")})
public class LineItem {

	private String id;
	
	private String name;
	
	private Integer quantity;
	
	private Double price;

	public LineItem() {
		super();
	}

	public LineItem(String id,String name, Integer quantity, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserProduct [name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}

	
	
}
