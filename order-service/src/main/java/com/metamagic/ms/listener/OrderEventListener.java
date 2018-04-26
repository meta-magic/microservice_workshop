package com.metamagic.ms.listener;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.metamagic.ms.events.integration.OrderCompletedEvent;
import com.metamagic.ms.events.integration.OrderPlacedEvent;
import com.metamagic.ms.exception.BussinessException;
import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.InvalidDataException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.service.OrderBusinessLogicService;

/**
 * @author sagar
 * 
 *         ORDER LISTNER
 */
@Component
public class OrderEventListener {

	@Autowired
	private OrderBusinessLogicService businessLogicService;

	@Autowired
	private KafkaTemplate<String, OrderCompletedEvent> kafkaTemplate;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "test_topic")
	public void receive(OrderPlacedEvent orderPlacedEvent) throws JsonParseException, JsonMappingException, IOException,
			BussinessException, RepositoryException, IllegalArgumentCustomException, InvalidDataException {
		System.out.println(orderPlacedEvent);
		latch.countDown();

		businessLogicService.save(orderPlacedEvent);

		kafkaTemplate.send("order_topic", new OrderCompletedEvent(orderPlacedEvent.getUserId()));
	}
}
