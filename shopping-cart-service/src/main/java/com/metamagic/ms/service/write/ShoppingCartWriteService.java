package com.metamagic.ms.service.write;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.metamagic.ms.aggregate.ShoppingCart;
import com.metamagic.ms.bean.Cart;
import com.metamagic.ms.commands.AddItemCommand;
import com.metamagic.ms.commands.CreateCartCommand;
import com.metamagic.ms.commands.PlaceOrderCommand;
import com.metamagic.ms.commands.RemoveItemCommand;

@Service
@Scope("request")
public class ShoppingCartWriteService {

	@Autowired
	private CommandGateway commandGateway;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	public void createCart(@RequestBody Cart cart){
		commandGateway.send(new CreateCartCommand(cart.getCartId(), cart.getCustomerId()));
	}
	
	public void addItem(@RequestBody Cart cart){
		commandGateway.send(new AddItemCommand(cart.getCartId(), cart.getItemId(), cart.getItemName(), cart.getQuantity(), cart.getPrice()));
	}
	
	public void removeItem(@RequestBody Cart cart){
		commandGateway.send(new RemoveItemCommand(cart.getCartId(), cart.getItemId(), cart.getItemName(), cart.getQuantity(), cart.getPrice()));
	}
	
	public void placeOrder(@RequestBody Cart cart){
		commandGateway.send(new PlaceOrderCommand(cart.getCartId(), cart.getCustomerId()));
	}
	
}
