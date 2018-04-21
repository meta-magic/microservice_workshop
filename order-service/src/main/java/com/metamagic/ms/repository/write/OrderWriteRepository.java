package com.metamagic.ms.repository.write;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 * THIS REPOSITORY USED FOR WRITE OPERATION
 */

public interface OrderWriteRepository {
	public OrderDocument save(OrderDocument order)throws RepositoryException;
}
