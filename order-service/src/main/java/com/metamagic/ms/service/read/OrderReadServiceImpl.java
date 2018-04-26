package com.metamagic.ms.service.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.entity.OrderDocument.Status;
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
	public List<OrderDocument> findAll(String userId) throws RepositoryException {
		List<OrderDocument> ordersList = orderReadRepository.findAll(userId);
		return ordersList;
	}

	// THIS METHOD RETURN ORDER ID WHICH IS IN PREPAREING STATE
	public String getOrderId(String userId) throws RepositoryException {
		OrderDocument document = orderReadRepository.findByUserIdAndStatus(userId, Status.PREPARING);
		return document.getOrderId();
	}

	// THIS METHOD RETRUN ORDER BY ID
	public OrderDocument findOrderById(String orderId) throws RepositoryException {
		OrderDocument orderDocument = orderReadRepository.findByOrderId(orderId);
		return orderDocument;
	}
}
