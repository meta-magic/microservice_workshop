package com.metamagic.ms.service.write;

import com.metamagic.ms.entity.OrderDocument;

/**
 * @author sagar
 *
 */
public interface OrderWriteService {
	public OrderDocument save(OrderDocument order);
}
