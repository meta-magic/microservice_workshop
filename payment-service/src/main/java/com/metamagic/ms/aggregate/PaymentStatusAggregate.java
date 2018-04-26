package com.metamagic.ms.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.metamagic.ms.commands.CompletePaymentCommand;
import com.metamagic.ms.commands.DeclinePaymentCommand;
import com.metamagic.ms.commands.StartPaymentProcessCommand;
import com.metamagic.ms.events.PaymentProcessStartedEvent;
import com.metamagic.ms.events.integration.PaymentCompletedEvent;
import com.metamagic.ms.events.integration.PaymentDeclinedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@AggregateRoot
public class PaymentStatusAggregate {

	@AggregateIdentifier
	private String statusId;
	
	private String orderId;
	
	private String paymentId;
	
	private String userId;
	
	private double amount;
	
	private String paymentMode;
	
	private String status;
	
	private String message;
	
	public PaymentStatusAggregate() {
	}
	
	@CommandHandler
	public PaymentStatusAggregate(StartPaymentProcessCommand paymenProcessCommand) {
		apply(new PaymentProcessStartedEvent(paymenProcessCommand.getStatusId(), paymenProcessCommand.getOrderId(),
				paymenProcessCommand.getPaymentId(), paymenProcessCommand.getUserId(), paymenProcessCommand.getAmount(),
				paymenProcessCommand.getPaymentMode()));
	}
	
	@EventSourcingHandler
	public void handle(PaymentProcessStartedEvent paymentProcessStartedEvent) {
		this.statusId = paymentProcessStartedEvent.getStatusId();
		this.orderId = paymentProcessStartedEvent.getOrderId();
		this.paymentId = paymentProcessStartedEvent.getPaymentId();
		this.userId = paymentProcessStartedEvent.getUserId();
		this.amount = paymentProcessStartedEvent.getAmount();
		this.paymentMode = paymentProcessStartedEvent.getPaymentMode();
	}
	
	@CommandHandler
	public void handleCommand(DeclinePaymentCommand declinePaymentCommand) {
		apply(new PaymentDeclinedEvent(orderId, paymentId, userId, declinePaymentCommand.getStatus(),
				declinePaymentCommand.getMessage()));
	}
	
	@EventSourcingHandler
	public void handleEvent(PaymentDeclinedEvent paymentDeclinedEvent) {
		this.orderId = paymentDeclinedEvent.getOrderId();
		this.paymentId = paymentDeclinedEvent.getPaymentId();
		this.userId = paymentDeclinedEvent.getUserId();
		this.status = paymentDeclinedEvent.getStatus();
		this.message = paymentDeclinedEvent.getMessage();
	}
	
	@CommandHandler
	public void handleCommand(CompletePaymentCommand completePaymentCommand) {
		apply(new PaymentCompletedEvent(orderId, paymentId, userId, completePaymentCommand.getStatus(),
				completePaymentCommand.getMessage()));
	}
	
	@EventSourcingHandler
	public void handleEvent(PaymentCompletedEvent paymentCompletedEvent) {
		this.orderId = paymentCompletedEvent.getOrderId();
		this.paymentId = paymentCompletedEvent.getPaymentId();
		this.userId = paymentCompletedEvent.getUserId();
		this.status = paymentCompletedEvent.getStatus();
		this.message = paymentCompletedEvent.getMessage();
	}
}
