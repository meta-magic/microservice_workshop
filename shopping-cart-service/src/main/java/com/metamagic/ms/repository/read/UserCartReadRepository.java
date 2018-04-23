package com.metamagic.ms.repository.read;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar THIS REPOSITORY INTERFACE IS USED FOR USER CART
 */
public interface UserCartReadRepository {
	public UserCart findByUserIdAndActive(String userId, String completed) throws RepositoryException;
}
