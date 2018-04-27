package com.metamagic.ms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.validation.CommonValidation;

/**
 * @author sagar THIS CLASS IS USED FOR USER CART DOCUMENTS
 */
@PersistenceCapable(table = "usercart", detachable = "true")
@DatastoreIdentity(customStrategy = "uuid")
public class UserCart implements CommonValidation {

	@PrimaryKey
	@Persistent(column = "_id", customValueStrategy = "uuid")
	public String id;

	@Persistent
	private String userId;

	@Persistent(defaultFetchGroup = "true")
	private Set<LineItem> lineItems;

	@Persistent
	private double total;

	@Persistent
	private String status;

	public UserCart() {

	}

	public UserCart(String id, String userId, Set<LineItem> lineItems) {
		super();
		this.id = id;
		this.userId = userId;
		this.lineItems = lineItems;
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

	public void setUserId(String userId) throws IllegalArgumentCustomException {
		if (!this.isValid(userId)) {
			throw new IllegalArgumentCustomException("User Id should not be null.");
		} else {
			this.userId = userId;
		}
	}

	public Set<LineItem> getlineItems() {
		return lineItems;
	}

	public void addOrUpdateProduct(String id, String name, Integer quantity, Double price, boolean remove) {
		if (this.lineItems == null) {
			this.lineItems = new HashSet<LineItem>();
		}

		LineItem lineItem = new LineItem(id, name, quantity, price);
		
		if(remove) {
			if(lineItems.remove(lineItem))
				this.subFromTotal(lineItem);
		} else {
			if(lineItems.add(lineItem))
				this.addToTotal(lineItem);
		}
		
	}

	private void addToTotal(LineItem lineItem) {
		this.total = total + lineItem.getPrice();
	}
	
	private void subFromTotal(LineItem lineItem) {
		this.total = total - lineItem.getPrice();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) throws IllegalArgumentCustomException {
		if (!this.isValid(total)) {
			throw new IllegalArgumentCustomException("Total should not be null.");
		} else {
			this.total = total;
		}
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) throws IllegalArgumentCustomException {
		if (!this.isValid(status)) {
			throw new IllegalArgumentCustomException("Status should not be null.");
		} else {
			this.status = status;
		}
	}

	@Override
	public String toString() {
		return "UserCart [id=" + id + ", userId=" + userId + ", lineItems=" + lineItems + "]";
	}

}
