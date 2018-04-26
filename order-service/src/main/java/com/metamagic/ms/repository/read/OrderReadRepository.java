package com.metamagic.ms.repository.read;

import java.util.List;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.entity.OrderDocument.Status;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * THIS REPOSITORY IS USED FOR READING ORDER OPERATION
 */
public interface OrderReadRepository {

	public List<OrderDocument> findAll(String userId) throws RepositoryException;

	public OrderDocument findByUserIdAndStatus(String userId, Status status) throws RepositoryException;

	public OrderDocument findByOrderId(String orderId) throws RepositoryException;
}
