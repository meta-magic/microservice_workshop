package com.metamagic.ms.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public final class CompletePaymentCommand {

	@TargetAggregateIdentifier
	private final String statusId;

	private final String status;

	private final String message;

	/**
	 * @param statusId
	 * @param status
	 * @param message
	 */
	public CompletePaymentCommand(String statusId, String status, String message) {
		super();
		this.statusId = statusId;
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
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
