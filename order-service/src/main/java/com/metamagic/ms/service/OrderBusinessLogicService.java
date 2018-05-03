package com.metamagic.ms.service;

import com.metamagic.ms.events.integration.OrderPlacedEvent;

public interface OrderBusinessLogicService {

	public void save(OrderPlacedEvent orderPlacedEvent) throws Exception;

}
