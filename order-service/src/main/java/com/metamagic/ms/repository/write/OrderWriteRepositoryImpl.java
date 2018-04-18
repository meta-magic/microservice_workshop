package com.metamagic.ms.repository.write;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar
 * 
 * THIS CLASS USED FOR WRITE ORDER OPERATION
 */
@Repository
public class OrderWriteRepositoryImpl extends GenericRepository<OrderDocument> implements OrderWriteRepository {

	@Override
	public OrderDocument save(OrderDocument order) {
		OrderDocument persistedObject = persist(order);
		return persistedObject;
	}

}
