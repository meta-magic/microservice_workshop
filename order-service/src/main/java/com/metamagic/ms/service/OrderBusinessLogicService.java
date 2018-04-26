package com.metamagic.ms.service;

import com.metamagic.ms.events.integration.OrderPlacedEvent;
import com.metamagic.ms.exception.BussinessException;
import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.InvalidDataException;
import com.metamagic.ms.exception.RepositoryException;

public interface OrderBusinessLogicService {

	public void save(OrderPlacedEvent orderPlacedEvent)
			throws BussinessException, IllegalArgumentCustomException, RepositoryException, InvalidDataException;

}
