package com.metamagic.ms.service.read;

import java.util.List;

import com.metamagic.ms.dto.OrderDTO;
import com.metamagic.ms.entity.Order;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 *
 */
public interface OrderReadService {
	public List<Order> findAll(String userId) throws RepositoryException;

	public OrderDTO getOrderDetails(String userId) throws RepositoryException;

	public Order findOrderById(String orderId) throws RepositoryException;
}
