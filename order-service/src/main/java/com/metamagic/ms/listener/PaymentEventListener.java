package com.metamagic.ms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.metamagic.ms.entity.Order;
import com.metamagic.ms.events.integration.PaymentCompletedEvent;
import com.metamagic.ms.events.integration.PaymentDeclinedEvent;
import com.metamagic.ms.service.read.OrderReadService;
import com.metamagic.ms.service.write.OrderWriteService;

/**
 * @author sagar
 * 
 *         THIS EVENT LISTENER CHECK PAYMENT STATUS
 */

@Component
public class PaymentEventListener {

	@Autowired
	private OrderReadService orderReadService;

	@Autowired
	private OrderWriteService orderWriteService;

	// THIS MEHTOD RECEIVE PAYMENTSUCCESSEVENT
	@KafkaListener(topics = "payment_success")
	public void receive(PaymentCompletedEvent paymentSuccessEvent) throws Exception {
		Order orderDocument = orderReadService.findOrderById(paymentSuccessEvent.getOrderId());
		orderDocument.markPaid();
		orderWriteService.save(orderDocument);
	}

	// THIS MEHTOD RECEIVE PAYMENTFAILUREEVENT
	@KafkaListener(topics = "payment_failure")
	public void receive(PaymentDeclinedEvent paymentFailureEvent) throws Exception {
		Order orderDocument = orderReadService.findOrderById(paymentFailureEvent.getOrderId());
		orderDocument.markPaymentFailure();
		orderWriteService.save(orderDocument);
	}

}
