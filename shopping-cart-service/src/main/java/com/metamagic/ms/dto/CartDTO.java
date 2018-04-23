package com.metamagic.ms.dto;

import java.io.Serializable;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.validation.CommonValidation;

/**
 * @author sagar THIS DTO USED TO CREATE CART
 */
public class CartDTO implements Serializable, CommonValidation {

	private String customerId;
	private String cartId;
	private String itemId;
	private String itemName;
	private Integer quantity;
	private Double price;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) throws IllegalArgumentCustomException {
		if (!this.isValid(customerId)) {
			throw new IllegalArgumentCustomException("Customer id should not be null");
		} else {
			this.customerId = customerId;
		}
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) throws IllegalArgumentCustomException {
		if (!this.isValid(cartId)) {
			throw new IllegalArgumentCustomException("Cart Id  should not be null");
		} else {
			this.cartId = cartId;
		}

	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) throws IllegalArgumentCustomException {
		if (!this.isValid(itemId)) {
			throw new IllegalArgumentCustomException("Item Id  should not be null");
		} else {
			this.itemId = itemId;
		}
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) throws IllegalArgumentCustomException {
		if (!this.isValid(itemName)) {
			throw new IllegalArgumentCustomException("item Name should not be null");
		} else {
			this.itemName = itemName;
		}

	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) throws IllegalArgumentCustomException {
		if (!this.isValid(quantity)) {
			throw new IllegalArgumentCustomException("Quantity Name should not be null");
		} else {
			this.quantity = quantity;
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
