package com.metamagic.ms.service.write;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar 
 * 
 * THIS ITERFACE IS USED FOR ORDER WRITE OPERATION
 */
public interface OrderWriteService {
	public OrderDocument save(OrderDocument order) throws RepositoryException;
}
