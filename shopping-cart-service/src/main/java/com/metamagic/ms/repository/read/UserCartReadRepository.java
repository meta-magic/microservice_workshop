package com.metamagic.ms.repository.read;

import com.metamagic.ms.entity.UserCart;

/**
 * @author sagar
 * THIS REPOSITORY INTERFACE IS USED FOR USER CART
 */
public interface UserCartReadRepository {
	public UserCart findByUserIdAndActive(String userId, String completed);
}
