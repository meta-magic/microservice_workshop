package com.metamagic.ms.aggregate;


import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.metamagic.ms.commands.AddItemCommand;
import com.metamagic.ms.commands.CreateCartCommand;
import com.metamagic.ms.commands.EmptyCartCommand;
import com.metamagic.ms.commands.PlaceOrderCommand;
import com.metamagic.ms.commands.RemoveItemCommand;
import com.metamagic.ms.events.CartCreatedEvent;
import com.metamagic.ms.events.ItemAddedEvent;
import com.metamagic.ms.events.ItemRemovedEvent;
import com.metamagic.ms.events.OrderPlacedEvent;

@Aggregate
@AggregateRoot
public class ShoppingCart {

	@AggregateIdentifier
	private String cartId;
	
	private String customerId;
	
	private Set<Items> items;
	
	public ShoppingCart(){
	}
	
	@CommandHandler
	public ShoppingCart(CreateCartCommand command){
		System.out.println(this.getClass()+" CreateCartCommand Start "+command);
		apply(new CartCreatedEvent(command.getCartId(), command.getCustomerId()));
		System.out.println(this.getClass()+" CreateCartCommand End "+command);
	}
	
	@EventSourcingHandler
	public void handle(CartCreatedEvent event){
		System.out.println(this.getClass()+" CartCreatedEvent Start "+event);
		this.cartId = event.getCartId();
		this.customerId = event.getCustomerId();
		this.items = new HashSet<Items>();
		System.out.println(this.getClass()+" CartCreatedEvent End "+event);
	}
	
	@CommandHandler
	public void handleCommand(AddItemCommand command) throws Exception{
		System.out.println(this.getClass()+" AddItemCommand Start "+command);
		Items items = new Items(command.getItemId(), command.getName(), command.getQuantity(), command.getPrice());
		ItemAddedEvent addedEvent = new ItemAddedEvent(this.cartId, this.customerId, items);
		apply(addedEvent);
		System.out.println(this.getClass()+" AddItemCommand End "+command);
	}
	
	@EventSourcingHandler
	public void handle(ItemAddedEvent event){
		System.out.println(this.getClass()+" ItemAddedEvent Start "+event);
		this.cartId = event.getCartId();
		this.customerId = event.getCustomerId();
		
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Items items2 = (Items) iterator.next();
			if(items2.getItemId().equals(event.getItems().getItemId())){
				iterator.remove();
			}
		}
		
		Items items = new Items(event.getItems().getItemId(), event.getItems().getName(), event.getItems().getQuantity(),event.getItems().getPrice());
		this.items.add(items);
		
		this.printCurrentState();
		System.out.println(this.getClass()+" ItemAddedEvent End "+event);
	}
	
	@CommandHandler
	public void handleCommand(RemoveItemCommand command) {
		System.out.println(this.getClass()+" RemoveItemCommand Start "+command);
		Items items = new Items(command.getItemId(), command.getName(), command.getQuantity(),command.getPrice());
		ItemRemovedEvent event = new ItemRemovedEvent(this.cartId, this.customerId, items);
		apply(event);
		System.out.println(this.getClass()+" RemoveItemCommand End "+command);
	}
	
	@EventSourcingHandler
	public void handle(ItemRemovedEvent event){
		System.out.println(this.getClass()+" ItemRemovedEvent Start "+event);
		this.cartId = event.getCartId();
		this.customerId = event.getCustomerId();
		
		
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Items items2 = (Items) iterator.next();
			if(items2.getItemId().equals(event.getItems().getItemId())){
				iterator.remove();
			}
		}
		
		this.printCurrentState();
		System.out.println(this.getClass()+" ItemRemovedEvent End "+event);
	}
	
	@CommandHandler
	public void handle(PlaceOrderCommand command){
		System.out.println(this.getClass()+" PlaceOrderCommand Start "+command);
		this.cartId = command.getCartId();
		this.customerId = command.getCustomerId();
		OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(cartId, customerId, items);
		apply(orderPlacedEvent);
		System.out.println(this.getClass()+" PlaceOrderCommand End "+command);
	}
	
	@CommandHandler
	public void handle(EmptyCartCommand emptyCartCommand){
		System.out.println(" +++++++++++++>  cart emptied ");
		markDeleted();
	}
	
	
	
	private void printCurrentState(){
		System.out.println("\n\n");
		System.out.println(this.toString());
		System.out.println("\n\n");
	}

	@Override
	public String toString() {
		return "ShoppingCart [cartId=" + cartId + ", customerId=" + customerId + ", items=" + items + "]";
	}
	
	
}
