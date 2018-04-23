package com.metamagic.ms.entity;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.validation.CommonValidation;

/**
 * @author sagar
 * 
 * THIS LINE ITEM IS SUBDOCUMENTS OF USER CART
 */
@PersistenceCapable
@EmbeddedOnly
@FetchGroup(name = "lineItems", members = { @Persistent(name = "id"), @Persistent(name = "name"),
		@Persistent(name = "quantity"), @Persistent(name = "price")})
public class LineItem implements CommonValidation{

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

	public void setName(String name) throws IllegalArgumentCustomException {
		if(!this.isValid(name)) {
			throw new IllegalArgumentCustomException("Name should not be null.");
		}else {
			this.name=name;
		}
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) throws IllegalArgumentCustomException {
		if(!this.isValid(quantity)) {
			throw new IllegalArgumentCustomException("Quantity should not be null.");
		}else {
			this.quantity = quantity;
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) throws IllegalArgumentCustomException {
		if(!this.isValid(price)) {
			throw new IllegalArgumentCustomException("Price should not be null.");
		}else {
			this.price = price;
		}
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
