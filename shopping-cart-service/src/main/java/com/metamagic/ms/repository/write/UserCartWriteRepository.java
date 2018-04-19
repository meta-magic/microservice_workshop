package com.metamagic.ms.repository.write;

import com.metamagic.ms.entity.UserCart;

/**
 * @author sagar
 * 
 * THIS INTERFACE IS USED FOR USER CART
 */
public interface UserCartWriteRepository {
	public void save(UserCart userCart);
}
