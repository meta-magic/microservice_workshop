package com.metamagic.ms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.User;
import com.metamagic.ms.bean.UserDTO;
import com.metamagic.ms.events.UserCreatedEvent;
import com.metamagic.ms.exception.CustomException;
import com.metamagic.ms.repository.read.UserReadRepository;
import com.metamagic.ms.repository.write.UserWriteRepository;
import com.metamagic.ms.service.write.UserWriteService;

/**
 * @author sagar
 *
 *         THIS SERVICE IS USED FOR WRITE OPERATION OF USERS
 */
@Service
public class UserWriteServiceImpl implements UserWriteService {

	@Autowired
	private UserWriteRepository userWriteRepository;

	@Autowired
	private UserReadRepository userReadRepository;

	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

	/**
	 * THIS METHOD IS USED TO CREATE USER
	 * 
	 */
	public void createUser(UserDTO userDTO) throws CustomException {

		User userStored = userReadRepository.findByUserId(userDTO.getUserId());
		if (userStored == null) {
			User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUserId(),
					userDTO.getPassword());
			userWriteRepository.save(user);
			Message<UserCreatedEvent> message = MessageBuilder.withPayload(new UserCreatedEvent(user.getId()))
					.setHeader(KafkaHeaders.TOPIC, "user_topic").setHeader("custom-header", "My custom header.").build();
			kafkaTemplate.send(message);
		} else {
			throw new CustomException("User with same userId is present, please choose other");
		}
	}
}
