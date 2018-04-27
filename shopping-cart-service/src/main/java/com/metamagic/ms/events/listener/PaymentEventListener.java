package com.metamagic.ms.events.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.events.integration.PaymentCompletedEvent;
import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.UserCartReadRepository;
import com.metamagic.ms.repository.write.UserCartWriteRepository;

/**
 * @author sagar
 * 
 *         THIS LISTENER IS USED FOR PAYMENT FAILURE EVENT
 */
@Component
public class PaymentEventListener {

	@Autowired
	private UserCartWriteRepository cartWriteRepository;

	@Autowired
	private UserCartReadRepository cartReadRepository;

	@KafkaListener(topics = "payment_success")
	public void receive(PaymentCompletedEvent paymentCompletedEvent)
			throws RepositoryException, IllegalArgumentCustomException {
		UserCart userCart = cartReadRepository.findByUserIdAndActive(paymentCompletedEvent.getUserId(), "PREPARING");
		userCart.setStatus(paymentCompletedEvent.getStatus());
		cartWriteRepository.save(userCart);
	}
}
