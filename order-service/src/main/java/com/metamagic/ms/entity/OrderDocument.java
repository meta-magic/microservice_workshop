package com.metamagic.ms.entity;

import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.validation.CommonValidation;

/**
 * @author sagar
 *
 */
@PersistenceCapable(table = "ui_designs", detachable = "true")
@DatastoreIdentity(customStrategy = "uuid")
public class OrderDocument implements CommonValidation {

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

	private String orderNumber;

	public OrderDocument() {
		super();
	}

	public OrderDocument(String cartId, String userId, Date date, Set<ItemDocument> items, double total, String status)
			throws IllegalArgumentCustomException {

		this.setCartId(cartId);
		this.setUserId(userId);
		this.setDate(date);
		this.setItems(items);
		this.setTotal(total);
		this.setStatus(status);

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

	private void setCartId(String cartId) throws IllegalArgumentCustomException {
		if (!this.isValid(cartId)) {
			throw new IllegalArgumentCustomException("Cart Id should not be null.");
		} else {
			this.cartId = cartId;
		}
	}

	public String getUserId() {
		return userId;
	}

	private void setUserId(String userId) throws IllegalArgumentCustomException {
		if (!this.isValid(userId)) {
			throw new IllegalArgumentCustomException("User Id should not be null.");
		} else {
			this.userId = userId;
		}
	}

	public Set<ItemDocument> getItems() {
		return items;
	}

	private void setItems(Set<ItemDocument> items) throws IllegalArgumentCustomException {
		if (!this.isValid(items)) {
			throw new IllegalArgumentCustomException("items should not be null.");
		} else {
			this.items = items;
		}
	}

	public Date getDate() {
		return date;
	}

	private void setDate(Date date) throws IllegalArgumentCustomException {
		if (!this.isValid(date)) {
			throw new IllegalArgumentCustomException("Date should not be null.");
		} else {
			this.date = date;
		}
	}

	public double getTotal() {
		return total;
	}

	private void setTotal(double total) throws IllegalArgumentCustomException {
		if (!this.isValid(total)) {
			throw new IllegalArgumentCustomException("total should not be null.");
		} else {
			this.total = total;
		}
	}

	public String getStatus() {
		return status;
	}

	private void setStatus(String status) throws IllegalArgumentCustomException {
		if (!this.isValid(status)) {
			throw new IllegalArgumentCustomException("status should not be null.");
		} else {
			this.status = status;
		}
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	private void setOrderNumber(String orderNumber) throws IllegalArgumentCustomException {
		if (!this.isValid(orderNumber)) {
			throw new IllegalArgumentCustomException("Order Number should not be null.");
		} else {
			this.orderNumber = orderNumber;
		}

	}

}
