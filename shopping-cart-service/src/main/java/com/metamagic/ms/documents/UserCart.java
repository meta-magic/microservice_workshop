package com.metamagic.ms.documents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usercart")
public class UserCart {

	@Id
	public String id;
	
	private String userId;
	
	private List<UserProduct> products;
	
	private double total;

	private boolean active;
	
	public UserCart() {
		super();
	}
	
	public UserCart(String userId) {
		super();
		this.userId = userId;
	}

	public UserCart(String id, String userId, List<UserProduct> products) {
		super();
		this.id = id;
		this.userId = userId;
		this.products = products;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserProduct> getProducts() {
		return products;
	}

	public void setProducts(List<UserProduct> products) {
		this.products = products;
	}
	
	
	public void addOrUpdateProduct(String id, String name, Integer quantity, Double price){
		if(this.products == null){
			this.products = new ArrayList<UserProduct>();
		}
		
		boolean add = true;
		for (Iterator<UserProduct> iterator = products.iterator(); iterator.hasNext();) {
			UserProduct userProduct = (UserProduct) iterator.next();
			if(userProduct.getId().equals(id)){
//				userProduct.setName(name);
//				userProduct.setQuantity(quantity);
//				userProduct.setPrice(price);
				add = false;
				iterator.remove();
			}
		}
		
		if(add) {
			UserProduct userProduct = new UserProduct(id, name, quantity, price);
			this.products.add(userProduct);
			this.addToTotal(userProduct);
		}
	}

	private void addToTotal(UserProduct userProduct) {
		this.total = total + userProduct.getPrice();
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void addProduct(String id, String name, Integer quantity, Double price){
		if(this.products == null){
			this.products = new ArrayList<UserProduct>();
		}
		UserProduct userProduct = new UserProduct(id, name, quantity, price);
		this.products.add(userProduct);
		this.addToTotal(userProduct);
	}

	@Override
	public String toString() {
		return "UserCart [id=" + id + ", userId=" + userId + ", products=" + products + "]";
	}
	
	
	
	
	
}
