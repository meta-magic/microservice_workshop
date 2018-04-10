package com.metamagic.ms.documents;

public class UserProduct {

	private String id;
	
	private String name;
	
	private Integer quantity;
	
	private Double price;

	public UserProduct() {
		super();
	}

	public UserProduct(String id,String name, Integer quantity, Double price) {
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
