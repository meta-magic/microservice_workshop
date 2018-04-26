package com.metamagic.ms.handler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.metamagic.ms.events.integration.PaymentCompletedEvent;
import com.metamagic.ms.events.integration.PaymentDeclinedEvent;

@Component
public class PaymentStatusHandler {

	@Autowired
	private KafkaTemplate<String, PaymentDeclinedEvent> kafkaTemplate1;

	@Autowired
	private KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate2;
	
	@EventHandler
	public void handle(PaymentDeclinedEvent paymentDeclinedEvent) {
		System.out.println(this.getClass() + " PaymentDeclinedEvent  handler");
		kafkaTemplate1.send("payment_failure", paymentDeclinedEvent);
	}
	
	@EventHandler
	public void handle(PaymentCompletedEvent paymentCompletedEvent) {
		System.out.println(this.getClass() + " PaymentCompletedEvent  handler");
		kafkaTemplate2.send("payment_success", paymentCompletedEvent);
	}
}
