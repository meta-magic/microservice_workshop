package com.metamagic.ms.bean;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.metamagic.ms.aggregate.Items;

@Document
public class Order {

	@Id
	private String id;

	private String cartId;

	private String customerId;

	private Date date;
	
	private Set<Items> items;
	
	private double total;

	private String status;
	
	public Order() {
		super();
	}
	
	public Order(String cartId, String customerId, Date date, Set<Items> items, double total, String status) {
		this.cartId = cartId;
		this.customerId = customerId;
		this.date = date;
		this.items = items;
		this.total = total;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
