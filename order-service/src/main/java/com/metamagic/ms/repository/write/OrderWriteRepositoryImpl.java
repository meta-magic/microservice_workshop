package com.metamagic.ms.repository.write;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.Order;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar
 * 
 * THIS CLASS USED FOR WRITE ORDER OPERATION
 */
@Repository
public class OrderWriteRepositoryImpl extends GenericRepository<Order> implements OrderWriteRepository {

	@Override
	public Order save(Order order) throws RepositoryException {
		Order persistedObject = persist(order);
		return persistedObject;
	}

}
