package com.metamagic.ms.events.handler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.events.CartCreatedEvent;
import com.metamagic.ms.events.ItemAddedEvent;
import com.metamagic.ms.events.ItemRemovedEvent;
import com.metamagic.ms.events.integration.OrderPlacedEvent;
import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.UserCartReadRepository;
import com.metamagic.ms.repository.write.UserCartWriteRepository;

/**
 * @author sagar THIS COMPONENT IS USED FOR SHOPPING CARD OPERATION
 */
@Component
public class ShoppingCartEventHandler {

	@Autowired
	private UserCartReadRepository cartReadRepository;

	@Autowired
	private UserCartWriteRepository cartWriteRepository;
	
	@Autowired
	private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

	@EventHandler
	public void handle(CartCreatedEvent cartCreatedEvent) {
		System.out.println(this.getClass() + " CartCreatedEvent");
	}

	@EventHandler
	public void handle(ItemAddedEvent event) throws RepositoryException, IllegalArgumentCustomException {
		System.out.println(this.getClass() + " ItemAddedEvent");
		this.updateUserCart(event.getCustomerId(), event.getItems().getItemId(), event.getItems().getName(),
				event.getItems().getQuantity(), event.getItems().getPrice(), false);
	}

	@EventHandler
	public void handle(ItemRemovedEvent event) throws RepositoryException, IllegalArgumentCustomException {
		System.out.println(this.getClass() + " ItemRemovedEvent");
		this.updateUserCart(event.getCustomerId(), event.getItems().getItemId(), event.getItems().getName(),
				event.getItems().getQuantity(), event.getItems().getPrice(), true);
	}

	@EventHandler
	public void handle(OrderPlacedEvent orderPlacedEvent) {
		System.out.println(this.getClass() + " OrderPlacedEvent");
		System.out.println(orderPlacedEvent.getItems());

		kafkaTemplate.send("test_topic", orderPlacedEvent);

		System.out.println("--kafkaTemplate--");
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
