package com.metamagic.ms.events.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.metamagic.ms.documents.UserCart;
import com.metamagic.ms.events.integration.OrderCompletedEvent;
import com.metamagic.ms.repository.UserCartRepository;

@Component
public class OrderCompletedEventListener {

	@Autowired
	private UserCartRepository userRepository;

	@KafkaListener(topics = "order_topic")
	public void handle(OrderCompletedEvent orderCompletedEvent) {
		UserCart userCart = userRepository.findByUserIdAndActive(orderCompletedEvent.getUserId(), false);

		if (userCart != null) {
			userCart.setActive(true);
			userRepository.save(userCart);
		}

	}
}
