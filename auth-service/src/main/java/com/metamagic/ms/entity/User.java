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
public class User implements CommonValidation {

	@PrimaryKey
	@Persistent(customValueStrategy = "uuid")
	private String id;

	private String firstName;

	private String lastName;

	private String userId;

	private String password;

	public User(String firstName, String lastName, String userId, String password)
			throws IllegalArgumentCustomException {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserId(userId);
		this.setPassword(password);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) throws IllegalArgumentCustomException {
		if (!this.isValid(firstName)) {
			throw new IllegalArgumentCustomException("First name should not be null.");
		} else {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) throws IllegalArgumentCustomException {
		if (!this.isValid(lastName)) {
			throw new IllegalArgumentCustomException("Last name should not be null.");
		} else {
			this.lastName = lastName;
		}
	}

	public String getUserId() {
		return userId;
	}

	protected void setUserId(String userId) throws IllegalArgumentCustomException {
		if (!this.isValid(userId)) {
			throw new IllegalArgumentCustomException("User id should not be null.");
		} else {
			this.userId = userId;
		}
	}

	public String getPassword() {
		return password;
	}

	protected void setPassword(String password) throws IllegalArgumentCustomException {
		if (!this.isValid(password)) {
			throw new IllegalArgumentCustomException("Password should not be null.");
		} else {
			this.password = password;
		}
	}
}
