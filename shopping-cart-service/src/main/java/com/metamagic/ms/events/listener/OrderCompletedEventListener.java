package com.metamagic.ms.events.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.events.integration.OrderCompletedEvent;
import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.UserCartReadRepository;
import com.metamagic.ms.repository.write.UserCartWriteRepository;

/**
 * @author sagar
 * 
 *  THIS LISTNER IS USED FOR HANDLE ORDER
 */
@Component
public class OrderCompletedEventListener {

	@Autowired
	private UserCartWriteRepository cartWriteRepository;

	@Autowired
	private UserCartReadRepository cartReadRepository;
	
	@KafkaListener(topics = "order_completed")
	public void handle(OrderCompletedEvent orderCompletedEvent) throws RepositoryException, IllegalArgumentCustomException {
		UserCart userCart = cartReadRepository.findByUserIdAndActive(orderCompletedEvent.getUserId(), null);

		if (userCart != null) {
			cartWriteRepository.save(userCart);
		}

	}
}
