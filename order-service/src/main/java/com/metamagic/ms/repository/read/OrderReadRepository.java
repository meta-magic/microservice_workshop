package com.metamagic.ms.repository.read;

import java.util.List;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.exception.RepositoryException;

public interface OrderReadRepository {

	public List<OrderDocument> findAll(String userId) throws RepositoryException;
}
