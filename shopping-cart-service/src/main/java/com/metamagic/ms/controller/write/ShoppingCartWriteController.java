package com.metamagic.ms.controller.write;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.aggregate.ShoppingCart;
import com.metamagic.ms.bean.Cart;
import com.metamagic.ms.service.write.ShoppingCartWriteService;

@RestController
@RequestMapping("/shoppingcart/write")
public class ShoppingCartWriteController {

	
	@Autowired
	private ShoppingCartWriteService shoppingCartService;
	
	@PostMapping("/create")
	public void createCart(@RequestBody Cart cart){
		shoppingCartService.createCart(cart);
	}
	
	@PostMapping("/additem")
	public void addItem(@RequestBody Cart cart){
		shoppingCartService.addItem(cart);
	}
	
	@PostMapping("/removeitem")
	public void removeItem(@RequestBody Cart cart){
		shoppingCartService.removeItem(cart);
	}
	
	@PostMapping("/placeorder")
	public void placeOrder(@RequestBody Cart cart){
		shoppingCartService.placeOrder(cart);
	}
	
	
}
