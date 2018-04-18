package com.metamagic.ms.events.handler;


import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.metamagic.ms.documents.UserCart;
import com.metamagic.ms.events.CartCreatedEvent;
import com.metamagic.ms.events.ItemAddedEvent;
import com.metamagic.ms.events.ItemRemovedEvent;
import com.metamagic.ms.events.OrderPlacedEvent;
import com.metamagic.ms.repository.UserCartRepository;

@Component
public class ShoppingCartEventHandler {

	@Autowired
	private UserCartRepository userRepository;
	
	
	
	@EventHandler
	public void handle(CartCreatedEvent cartCreatedEvent){
		System.out.println(this.getClass()+" CartCreatedEvent");
	}
	
	@EventHandler
	public void handle(ItemAddedEvent event){
		System.out.println(this.getClass()+" ItemAddedEvent");
		this.updateUserCart(event.getCustomerId(), event.getItems().getItemId(), 
				event.getItems().getName(), event.getItems().getQuantity(), 
				event.getItems().getPrice());
	}
	
	@EventHandler
	public void handle(ItemRemovedEvent event){
		System.out.println(this.getClass()+" ItemRemovedEvent");
		this.updateUserCart(event.getCustomerId(), event.getItems().getItemId(), 
				event.getItems().getName(), event.getItems().getQuantity(), 
				event.getItems().getPrice());
	}
	
    @Autowired
    private KafkaTemplate  kafkaTemplate;

	@EventHandler
	public void handle(OrderPlacedEvent orderPlacedEvent){
		System.out.println(this.getClass()+" OrderPlacedEvent");
		System.out.println(orderPlacedEvent.getItems());
		
		kafkaTemplate.send("test_topic",orderPlacedEvent);
		
		System.out.println("--kafkaTemplate--");
	}
	
	private void updateUserCart(String userId, String itemId, String name, Integer quantity, Double price) {
		UserCart userCart = userRepository.findByUserIdAndActive(userId, false);
		if (userCart == null) {
			userCart = new UserCart();
			userCart.setUserId(userId);
		}
		userCart.addOrUpdateProduct(itemId, name, quantity, price);

		userRepository.save(userCart);
	}
	
}
