package com.metamagic.ms.repository.write;

import com.metamagic.ms.entity.OrderDocument;

/**
 * @author sagar
 * 
 * THIS REPOSITORY USED FOR WRITE OPERATION
 */

public interface OrderWriteRepository {
	public OrderDocument save(OrderDocument order);
}
