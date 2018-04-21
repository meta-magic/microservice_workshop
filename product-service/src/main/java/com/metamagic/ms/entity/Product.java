package com.metamagic.ms.entity;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.validation.CommonValidation;

/**
 * @author sagar
 *
 */
@PersistenceCapable(detachable = "true")
public class Product implements CommonValidation {

	@PrimaryKey
	@Persistent(column = "_id", customValueStrategy = "uuid")
	private String id;

	private String name;

	private Double price;

	private String desc;

	private String image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) throws IllegalArgumentCustomException {
		if (!this.isValid(name)) {
			throw new IllegalArgumentCustomException("Name should not be null");
		} else {
			this.name = name;
		}
	}

	public Double getPrice() {
		return price;
	}

	private void setPrice(Double price) throws IllegalArgumentCustomException {
		if (!this.isValid(price)) {
			throw new IllegalArgumentCustomException("Price should not be null");
		} else {
			this.price = price;
		}

	}

	public String getDesc() {
		return desc;
	}

	private void setDesc(String desc) throws IllegalArgumentCustomException {
		if (!this.isValid(desc)) {
			throw new IllegalArgumentCustomException("Description should not be null");
		} else {
			this.desc = desc;
		}
		
	}

	public String getImage() {
		return image;
	}

	private void setImage(String image) throws IllegalArgumentCustomException {
		if (!this.isValid(image)) {
			throw new IllegalArgumentCustomException("Image should not be null");
		} else {
			this.image = image;
		}
		
	}

}
