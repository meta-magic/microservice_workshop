package com.metamagic.ms.events;

public final class PaymentProcessStartedEvent {

	private String statusId;

	private String orderId;

	private String paymentId;
	
	private String userId;

	private double amount;

	private String paymentMode;

	/**
	 * @param statusId
	 * @param orderId
	 * @param paymentId
	 * @param amount
	 * @param paymentMode
	 */
	public PaymentProcessStartedEvent(String statusId, String orderId, String paymentId, String userId, double amount,
			String paymentMode) {
		super();
		this.statusId = statusId;
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.userId = userId;
		this.amount = amount;
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	
}
