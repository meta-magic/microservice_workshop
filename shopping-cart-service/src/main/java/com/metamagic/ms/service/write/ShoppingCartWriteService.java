package com.metamagic.ms.service.write;

import org.springframework.web.bind.annotation.RequestBody;

import com.metamagic.ms.dto.CartDTO;

/**
 * @author sagar
 * 
 * THIS INTERFACE IS USED FOR SHOPPING CART WRITE OPERATION
 */
public interface ShoppingCartWriteService {
	
	public void createCart(@RequestBody CartDTO cart);

	public void addItem(@RequestBody CartDTO cart);

	public void removeItem(@RequestBody CartDTO cart);

	public void placeOrder(@RequestBody CartDTO cart);

}
