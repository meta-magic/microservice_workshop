package com.metamagic.ms.repository.write;

import com.metamagic.ms.entity.Order;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 * THIS REPOSITORY USED FOR WRITE OPERATION
 */

public interface OrderWriteRepository {
	public Order save(Order order)throws RepositoryException;
}
