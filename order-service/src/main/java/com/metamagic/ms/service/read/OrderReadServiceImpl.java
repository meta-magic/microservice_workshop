package com.metamagic.ms.service.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.entity.Order;
import com.metamagic.ms.entity.Order.Status;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.OrderReadRepository;

/**
 * @author sagar THIS ORDERSERVICE ISED FOR READ OPERATION OF ORDER
 */
@Service
public class OrderReadServiceImpl implements OrderReadService {

	@Autowired
	private OrderReadRepository orderReadRepository;

	// THIS METHOD IS RETURN ORDERS BY USERID
	public List<Order> findAll(String userId) throws RepositoryException {
		List<Order> ordersList = orderReadRepository.findAll(userId);
		return ordersList;
	}

	// THIS METHOD RETURN ORDER DTO WHICH IS IN PREPAREING STATE
	public Order getOrderDetails(String userId) throws RepositoryException {
		Order document = orderReadRepository.findByUserIdAndStatus(userId, Status.PREPARING);
		return document;
	}

	// THIS METHOD RETRUN ORDER BY ID
	public Order findOrderById(String orderId) throws RepositoryException {
		Order orderDocument = orderReadRepository.findByOrderId(orderId);
		return orderDocument;
	}
}
