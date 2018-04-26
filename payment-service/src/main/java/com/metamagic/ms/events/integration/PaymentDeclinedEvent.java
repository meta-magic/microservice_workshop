package com.metamagic.ms.events.integration;

import java.io.Serializable;

public final class PaymentDeclinedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3391996758147882617L;

	private final String orderId;

	private final String paymentId;

	private final String userId;

	private final String status;

	private final String message;

	/**
	 * @param orderId
	 * @param paymentId
	 * @param userId
	 * @param status
	 * @param message
	 */
	public PaymentDeclinedEvent(String orderId, String paymentId, String userId, String status, String message) {
		super();
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.userId = userId;
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @return the paymentId
	 */
	public String getPaymentId() {
		return paymentId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
}
