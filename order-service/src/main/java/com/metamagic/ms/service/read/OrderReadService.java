package com.metamagic.ms.service.read;

import java.util.List;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 *
 */
public interface OrderReadService {
	public List<OrderDocument> findAll() throws RepositoryException;

	public String getOrderId() throws RepositoryException;
}
