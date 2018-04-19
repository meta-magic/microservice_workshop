package com.metamagic.ms.service.write;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.metamagic.ms.aggregate.ShoppingCart;
import com.metamagic.ms.commands.AddItemCommand;
import com.metamagic.ms.commands.CreateCartCommand;
import com.metamagic.ms.commands.PlaceOrderCommand;
import com.metamagic.ms.commands.RemoveItemCommand;
import com.metamagic.ms.dto.CartDTO;

@Service
@Scope("request")
public class ShoppingCartWriteServiceImpl implements ShoppingCartWriteService {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private ShoppingCart shoppingCart;
	
	/**
	 * THIS METHOD IS USED FOR CREATE CART
	 * 
	 * */
	public void createCart(@RequestBody CartDTO cart) {
		commandGateway.send(new CreateCartCommand(cart.getCartId(), cart.getCustomerId()));
	}

	/**
	 * THIS METHOD IS USED FOR ADD ITEM
	 * 
	 * */
	public void addItem(@RequestBody CartDTO cart) {
		commandGateway.send(new AddItemCommand(cart.getCartId(), cart.getItemId(), cart.getItemName(),
				cart.getQuantity(), cart.getPrice()));
	}

	/**
	 * THIS METHOD iS USED FOR REMOVE ITEM
	 * 
	 * */
	public void removeItem(@RequestBody CartDTO cart) {
		commandGateway.send(new RemoveItemCommand(cart.getCartId(), cart.getItemId(), cart.getItemName(),
				cart.getQuantity(), cart.getPrice()));
	}
    
	/**
	 * THIS METHOD IS USED FOR PLACE ORDER
	 * */
	public void placeOrder(@RequestBody CartDTO cart) {
		commandGateway.send(new PlaceOrderCommand(cart.getCartId(), cart.getCustomerId()));
	}

}
