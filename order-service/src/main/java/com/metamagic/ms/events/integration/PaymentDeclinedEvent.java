package com.metamagic.ms.events.integration;

import java.io.Serializable;

/**
 * @author sagar
 * 
 *         THIS EVENT IS USED WHEN PAYMENT IS FAILURE
 */
public class PaymentDeclinedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2614561874313282922L;

	private String userId;

	private String orderId;

	private String paymentId;

	private String status;

	private String message;

	public PaymentDeclinedEvent() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
