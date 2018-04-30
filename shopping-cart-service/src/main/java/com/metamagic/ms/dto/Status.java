package com.metamagic.ms.dto;

/**
 * @author sagar
 *
 */
public enum Status {

	/**
	 * Placed, but not payed yet. Still changeable.
	 */
	PAYMENT_EXPECTED,

	/**
	 * Payment initiated
	 */

	PAYMENT_INITIATED,
	/**
	 * {@link Order} was payed. No changes allowed to it anymore.
	 */
	PAID,
	/**
	 * PAYMENT FAILURE
	 */
	PAYMENT_FAILURE,
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
