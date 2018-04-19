package com.metamagic.ms.events.listener;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.metamagic.ms.commands.CreateCartCommand;
import com.metamagic.ms.events.integration.UserCreatedEvent;

/**
 * @author sagar
 * THIS LISTENER USED FOR CREATING USER
 */
@Component
public class UserCreatedEventListener {

	@Autowired
	private CommandGateway commandGateway;

	@KafkaListener(topics = "user_topic")
	public void userCreated(@Payload UserCreatedEvent userCreatedEvent, @Header(name="custom-header") String header) {
		System.out.println(" +++++++++++++++++ "+ userCreatedEvent);
		System.out.println(" +++++++++++++++++ "+ header);
		commandGateway.send(new CreateCartCommand(userCreatedEvent.getUserId(), userCreatedEvent.getUserId()));
	}
}
