package com.metamagic.ms.events.listener;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.metamagic.ms.commands.CreateCartCommand;
import com.metamagic.ms.events.handler.ShoppingCartEventHandler;
import com.metamagic.ms.events.integration.UserCreatedEvent;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 * THIS LISTENER USED FOR CREATING USER
 */
@Component
public class UserCreatedEventListener {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserCreatedEventListener.class);

	@Autowired
	private CommandGateway commandGateway;

	@KafkaListener(topics = "user_created")
	public void userCreated(@Payload UserCreatedEvent userCreatedEvent, @Header(name="custom-header") String userid) {
		log.debug("UserCreatedEvent : "+userid);
		commandGateway.send(new CreateCartCommand(userCreatedEvent.getUserId(), userCreatedEvent.getUserId()));
	}
}
