package com.metamagic.ms.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.InvalidDataException;
import com.metamagic.ms.validation.CommonValidation;

/**
 * @author sagar
 *
 */
@PersistenceCapable(table = "order", detachable = "true")
@DatastoreIdentity(customStrategy = "uuid")
public class OrderDocument implements CommonValidation {

	@PrimaryKey
	@Persistent(column = "_id", customValueStrategy = "uuid")
	private String orderId;

	@Persistent
	private String cartId;

	@Persistent
	private String userId;

	@Persistent
	private Date date;

	@Persistent(mappedBy = "order", defaultFetchGroup="true")
	private Set<ItemDocument> items;

	@Persistent(mappedBy = "order", defaultFetchGroup = "true")
	private ShippingAddress shippingAddress;

	@Persistent(mappedBy = "order", defaultFetchGroup = "true")
	private Payment payment;

	@Persistent(defaultFetchGroup = "true")
	private MoneytoryValue moneytoryValue;

	private double total;

	private Status status;

	private String orderNumber;

	public OrderDocument() {
		super();
	}

	public OrderDocument(String cartId, String userId, Date date, Set<ItemDocument> items, double total, Status status)
			throws IllegalArgumentCustomException {

		this.setCartId(cartId);
		this.setUserId(userId);
		this.setDate(date);
		this.setItems(items);
		this.setTotal(total);
		this.setStatus(status);
		this.moneytoryValue();
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public Payment getPayment() {
		return payment;
	}

	public MoneytoryValue getMoneytoryValue() {
		return moneytoryValue;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 
	 * @return {@link MoneytoryValue}
	 */
	public MoneytoryValue moneytoryValue() {
		moneytoryValue = new MoneytoryValue(getTotal(), "USD");
		return moneytoryValue;
	}

	public void addLineItem(String itemId, String itemName, Double price, Integer quantity)
			throws InvalidDataException, IllegalArgumentCustomException {
		ItemDocument lineItem = new ItemDocument(itemId, itemName, price, quantity, this);
		items.add(lineItem);
	}

	public void initCart() {
		this.items = new HashSet<ItemDocument>();
	}

	/**
	 * Set order date an order no
	 */
	private void generateOrderNo() {
		this.date = new Date(Calendar.getInstance().getTimeInMillis());
		this.orderNumber = "OD" + date.getTime() + "";
	}

	public Double getTotal() {
		total = 0.0;
		for (Iterator<ItemDocument> iterator = items.iterator(); iterator.hasNext();) {
			ItemDocument lineItem = (ItemDocument) iterator.next();
			total = total + lineItem.getSubTotal();
		}
		return total;
	}

	/**
	 * 
	 * @return orderdate {@link Date}
	 */
	public void updateOrderDate() {
		this.date = new Date();
	}

	/**
	 * Maps cart status as open
	 */
	public void markPaymentExepected() {
		this.status = Status.PAYMENT_EXPECTED;
	}

	/**
	 * Maps cart status as close
	 */
	public void markPaid() throws InvalidDataException {
		if (this.shippingAddress == null) {
			throw new InvalidDataException("Invalid state exception");
		}
		this.status = Status.PAID;
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

	private void setTotal(double total) throws IllegalArgumentCustomException {
		if (!this.isValid(total)) {
			throw new IllegalArgumentCustomException("total should not be null.");
		} else {
			this.total = total;
		}
	}

	public Status getStatus() {
		return status;
	}

	private void setStatus(Status status) throws IllegalArgumentCustomException {
		if (!this.isValid(status)) {
			throw new IllegalArgumentCustomException("status should not be null.");
		} else {
			this.status = status;
		}
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Add the shipping address to order
	 * 
	 * @param shippinglabel
	 * @param address
	 * @param country
	 * @param province
	 * @param postalcode
	 * @param city
	 * @throws InvalidDataException
	 */
	public void addShippingAddress(String shippinglabel, String address, String country, String province,
			String postalcode, String city) throws InvalidDataException {
		if (this.shippingAddress == null) {
			ShippingAddress shippingAddress = new ShippingAddress(shippinglabel, address, country, province, postalcode,
					city, this);
			this.shippingAddress = shippingAddress;
		} else {
			this.shippingAddress.updateShippingAddress(shippinglabel, address, country, province, postalcode, city);
		}
	}

	/**
	 * Add the payment details
	 * 
	 * @param paymentmode
	 * @throws InvalidDataException
	 */
	public void addPaymentDetails(String paymentmode) throws InvalidDataException {
		Payment payment = new Payment(paymentmode, getTotal(), this);
		this.payment = payment;
		this.markPaid();
	}

	private void setOrderNumber(String orderNumber) throws IllegalArgumentCustomException {
		if (!this.isValid(orderNumber)) {
			throw new IllegalArgumentCustomException("Order Number should not be null.");
		} else {
			this.orderNumber = orderNumber;
		}

	}

	public static enum Status {

		/**
		 * Placed, but not payed yet. Still changeable.
		 */
		PAYMENT_EXPECTED,

		/**
		 * {@link Order} was payed. No changes allowed to it anymore.
		 */
		PAID,

		/**
		 * The {@link Order} is currently processed.
		 */
		PREPARING,

		/**
		 * The {@link Order} is ready to be picked up by the customer.
		 */
		READY,

		/**
		 * The {@link Order} was completed.
		 */
		TAKEN;
	}

}
