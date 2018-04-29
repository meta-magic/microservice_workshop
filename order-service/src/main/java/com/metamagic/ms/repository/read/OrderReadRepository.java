package com.metamagic.ms.repository.read;

import java.util.List;

import com.metamagic.ms.entity.Order;
import com.metamagic.ms.entity.Order.Status;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * THIS REPOSITORY IS USED FOR READING ORDER OPERATION
 */
public interface OrderReadRepository {

	public List<Order> findAll(String userId) throws RepositoryException;

	public Order findByUserIdAndStatus(String userId, Status status) throws RepositoryException;

	public Order findByOrderId(String orderId) throws RepositoryException;
}
