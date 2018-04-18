package com.metamagic.ms.entity;

import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author sagar
 *
 */
@PersistenceCapable(table = "ui_designs", detachable = "true")
@DatastoreIdentity(customStrategy = "uuid")
public class OrderDocument {

	@PrimaryKey
	@Persistent(column = "_id", customValueStrategy = "uuid")
	private String id;

	@Persistent
	private String cartId;

	@Persistent
	private String userId;

	@Persistent
	private Date date;

	@Persistent
	private Set<ItemDocument> items;

	private double total;

	private String status;

	public OrderDocument() {
		super();
	}

	public OrderDocument(String cartId, String userId, Date date, Set<ItemDocument> items, double total,
			String status) {
		this.cartId = cartId;
		this.userId = userId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<ItemDocument> getItems() {
		return items;
	}

	public void setItems(Set<ItemDocument> items) {
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
