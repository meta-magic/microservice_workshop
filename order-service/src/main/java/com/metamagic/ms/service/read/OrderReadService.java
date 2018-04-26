package com.metamagic.ms.service.read;

import java.util.List;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 *
 */
public interface OrderReadService {
	public List<OrderDocument> findAll(String userId) throws RepositoryException;

	public String getOrderId(String userId) throws RepositoryException;

	public OrderDocument findOrderById(String orderId) throws RepositoryException;
}
