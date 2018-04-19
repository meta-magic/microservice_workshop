package com.metamagic.ms.service.read;

import com.metamagic.ms.entity.UserCart;

/**
 * @author sagar
 * 
 * THIS INTERFACE IS USED FOR SHOPPING CART READ OPERATION
 */
public interface ShoppingCartReadService {
	public UserCart fetchcart(String id);
}
