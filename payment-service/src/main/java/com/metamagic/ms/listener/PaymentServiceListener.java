package com.metamagic.ms.listener;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.metamagic.ms.commands.StartPaymentProcessCommand;
import com.metamagic.ms.events.integration.PaymentInitiatedEvent;

/**
 * @author Ashutosh.Jadhav
 *
 *	Listener class that listens to the order service events
 */
@Component
public class PaymentServiceListener {

	@Autowired
	private CommandGateway commandGateway;
	
	@KafkaListener(topics = "order_payment")
	public void listen(PaymentInitiatedEvent paymentInitiatedEvent) {
		String statusId = UUID.randomUUID().toString();
		commandGateway.send(new StartPaymentProcessCommand(statusId, paymentInitiatedEvent.getOrderId(),
				paymentInitiatedEvent.getPaymentId(), paymentInitiatedEvent.getUserId(),
				paymentInitiatedEvent.getAmount(), paymentInitiatedEvent.getPaymentMode()));
	}
}
