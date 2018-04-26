package com.metamagic.ms.events.integration;

import java.io.Serializable;

/**
 * @author sagar
 * 
 *         THIS EVENT USED TO ADDING PAYMENT DETAILS
 */
public class PaymentInitiatedEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1895032081616520400L;

	private String orderId;

	private String paymentId;
	
	private String userId;

	private Double amount;

	private String paymentMode;

	public PaymentInitiatedEvent() {

	}

	public PaymentInitiatedEvent(String orderId, String paymentId, String userId, Double amount, String paymentMode) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.userId = userId;
		this.amount = amount;
		this.paymentMode = paymentMode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

}