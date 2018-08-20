package com.metamagic.ms.listener;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
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

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 * 
 *         ORDER LISTNER
 */
@Component
public class OrderEventListener {

	private static final Logger log = (Logger) LoggerFactory.getLogger(OrderEventListener.class);
	
	@Autowired
	private OrderBusinessLogicService businessLogicService;

	@Autowired
	private KafkaTemplate<String, OrderCompletedEvent> kafkaTemplate;


	@KafkaListener(topics = "order_placed")
	public void receive(OrderPlacedEvent orderPlacedEvent, @Header(name="custom-header") String userid) throws JsonParseException, JsonMappingException, IOException,
			BussinessException, RepositoryException, IllegalArgumentCustomException, InvalidDataException {
		log.debug("OrderPlacedEvent "+userid);
		try {
			businessLogicService.save(orderPlacedEvent);
		} catch (Exception e) {
			log.error("OrderPlacedEvent "+userid);
			e.printStackTrace();
		}

		kafkaTemplate.send("order_completed", new OrderCompletedEvent(orderPlacedEvent.getUserId()));
	}
}
