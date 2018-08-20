package com.metamagic.ms.events.handler;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.events.CartCreatedEvent;
import com.metamagic.ms.events.ItemAddedEvent;
import com.metamagic.ms.events.ItemRemovedEvent;
import com.metamagic.ms.events.integration.EmptyCartEvent;
import com.metamagic.ms.events.integration.OrderPlacedEvent;
import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.UserCartReadRepository;
import com.metamagic.ms.repository.write.UserCartWriteRepository;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar THIS COMPONENT IS USED FOR SHOPPING CARD OPERATION
 */
@Component
public class ShoppingCartEventHandler {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ShoppingCartEventHandler.class);


	@Autowired
	private UserCartReadRepository cartReadRepository;

	@Autowired
	private UserCartWriteRepository cartWriteRepository;
	
	@Autowired
	private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

	@EventHandler
	public void handle(CartCreatedEvent cartCreatedEvent) {
		log.debug(" CartCreatedEvent");
	}

	@EventHandler
	public void handle(ItemAddedEvent event) throws RepositoryException, IllegalArgumentCustomException {
		log.debug(" ItemAddedEvent");
		this.updateUserCart(event.getCustomerId(), event.getItems().getItemId(), event.getItems().getName(),
				event.getItems().getQuantity(), event.getItems().getPrice(), false);
	}
 
	@EventHandler
	public void handle(ItemRemovedEvent event) throws RepositoryException, IllegalArgumentCustomException {
		log.debug(" ItemRemovedEvent");
		this.updateUserCart(event.getCustomerId(), event.getItems().getItemId(), event.getItems().getName(),
				event.getItems().getQuantity(), event.getItems().getPrice(), true);
	}

	@EventHandler
	public void handle(OrderPlacedEvent orderPlacedEvent) {
		log.debug("OrderPlacedEvent");
		Message<OrderPlacedEvent> message = MessageBuilder.withPayload(orderPlacedEvent)
				.setHeader(KafkaHeaders.TOPIC, "order_placed").setHeader("custom-header", orderPlacedEvent.getUserId()).build();
		
		kafkaTemplate.send(message);
	}
	
	@EventHandler
	public void handle(EmptyCartEvent emptyCartEvent) throws IllegalArgumentCustomException, RepositoryException {
		log.debug("EmptyCartEvent");
		UserCart userCart = cartReadRepository.findByUserIdAndActive(emptyCartEvent.getUserId(), null);
		userCart.setStatus("COMPLETED");
		cartWriteRepository.save(userCart);
	}

	private void updateUserCart(String userId, String itemId, String name, Integer quantity, Double price, boolean remove) throws RepositoryException, IllegalArgumentCustomException {
		UserCart userCart = cartReadRepository.findByUserIdAndActive(userId, null);
		if (userCart == null) {
			userCart = new UserCart();
			userCart.setUserId(userId);
		}
		userCart.addOrUpdateProduct(itemId, name, quantity, price, remove);

		cartWriteRepository.save(userCart);
	}

}
