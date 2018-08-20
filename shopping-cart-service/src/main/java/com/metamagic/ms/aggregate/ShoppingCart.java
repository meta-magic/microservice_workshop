package com.metamagic.ms.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.LoggerFactory;

import com.metamagic.ms.commands.AddItemCommand;
import com.metamagic.ms.commands.CreateCartCommand;
import com.metamagic.ms.commands.EmptyCartCommand;
import com.metamagic.ms.commands.PlaceOrderCommand;
import com.metamagic.ms.commands.RemoveItemCommand;
import com.metamagic.ms.events.CartCreatedEvent;
import com.metamagic.ms.events.ItemAddedEvent;
import com.metamagic.ms.events.ItemRemovedEvent;
import com.metamagic.ms.events.integration.EmptyCartEvent;
import com.metamagic.ms.events.integration.OrderPlacedEvent;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 *
 */
@Aggregate
@AggregateRoot
public class ShoppingCart {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ShoppingCart.class);

	@AggregateIdentifier
	private String cartId;

	private String customerId;

	private Set<Items> items;

	public ShoppingCart() {
	}

	@CommandHandler
	public ShoppingCart(CreateCartCommand command) {
		log.debug(" CreateCartCommand  ");
		apply(new CartCreatedEvent(command.getCartId(), command.getCustomerId()));
	}

	@EventSourcingHandler
	public void handle(CartCreatedEvent event) {
		log.debug(" CartCreatedEvent ");
		this.cartId = event.getCartId();
		this.customerId = event.getCustomerId();
		this.items = new HashSet<Items>();
	}

	@CommandHandler
	public void handleCommand(AddItemCommand command) throws Exception {
		log.debug(" AddItemCommand  ");
		Items items = new Items(command.getItemId(), command.getName(), command.getQuantity(), command.getPrice());
		ItemAddedEvent addedEvent = new ItemAddedEvent(this.cartId, this.customerId, items);
		apply(addedEvent);
		
	}

	@EventSourcingHandler
	public void handle(ItemAddedEvent event) {
		log.debug(" ItemAddedEvent  ");
		this.cartId = event.getCartId();
		this.customerId = event.getCustomerId();

		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Items items2 = (Items) iterator.next();
			if (items2.getItemId().equals(event.getItems().getItemId())) {
				iterator.remove();
			}
		}

		Items items = new Items(event.getItems().getItemId(), event.getItems().getName(),
				event.getItems().getQuantity(), event.getItems().getPrice());
		this.items.add(items);
		
	}

	@CommandHandler
	public void handleCommand(RemoveItemCommand command) {
		log.debug(" RemoveItemCommand  ");
		Items items = new Items(command.getItemId(), command.getName(), command.getQuantity(), command.getPrice());
		ItemRemovedEvent event = new ItemRemovedEvent(this.cartId, this.customerId, items);
		apply(event);
		
	}

	@EventSourcingHandler
	public void handle(ItemRemovedEvent event) {
		log.debug(" ItemRemovedEvent  ");
		this.cartId = event.getCartId();
		this.customerId = event.getCustomerId();

		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Items items2 = (Items) iterator.next();
			if (items2.getItemId().equals(event.getItems().getItemId())) {
				iterator.remove();
			}
		}
		
	}

	@CommandHandler
	public void handle(PlaceOrderCommand command) {
		log.debug(" PlaceOrderCommand  ");
		this.cartId = command.getCartId();
		this.customerId = command.getCustomerId();
		OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(cartId, customerId, items);
		apply(orderPlacedEvent);
		
	}

	@EventSourcingHandler
	public void handle(OrderPlacedEvent event) {
		log.debug(" OrderPlacedEvent  ");
		this.cartId = event.getCartId();
		this.customerId = event.getUserId();
	}

	@CommandHandler
	public void handle(EmptyCartCommand command) {
		log.debug(" EmptyCartCommand  ");
		this.cartId = command.getCartId();
		this.customerId = command.getCustomerId();
		apply(new EmptyCartEvent(command.getCustomerId()));
		
	}
	
	@EventSourcingHandler
	public void handle(EmptyCartEvent event) {
		log.debug(" EmptyCartEvent  ");
		this.cartId = event.getUserId();
		this.customerId = event.getUserId();
		 this.items = new HashSet<Items>();
		
	}

	private void printCurrentState() {
		log.debug(this.toString());
	}

	@Override
	public String toString() {
		return "ShoppingCart [cartId=" + cartId + ", customerId=" + customerId + ", items=" + items + "]";
	}

}
