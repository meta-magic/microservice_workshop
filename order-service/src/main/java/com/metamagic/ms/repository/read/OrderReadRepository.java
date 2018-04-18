package com.metamagic.ms.repository.read;

import java.util.List;

import com.metamagic.ms.entity.OrderDocument;

public interface OrderReadRepository {
	
	public List<OrderDocument> findAll(String userId);
}
