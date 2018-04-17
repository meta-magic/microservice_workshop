package com.metamagic.ms.service.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.metamagic.ms.bean.User;
import com.metamagic.ms.bean.UserDTO;
import com.metamagic.ms.events.UserCreatedEvent;
import com.metamagic.ms.exception.CustomException;
import com.metamagic.ms.repository.read.UserReadRepository;
import com.metamagic.ms.repository.write.UserWriteRepository;

@Component
public class UserWriteService {

	@Autowired
	private UserWriteRepository userWriteRepository;
	
	@Autowired
	private UserReadRepository userReadRepository;
	
	@Autowired
	private KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
	
	public void createUser(UserDTO userDTO) throws CustomException {
		
		User userStored = userReadRepository.findByUserId(userDTO.getUserId()); 
		if(userStored == null) {
			User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUserId(), userDTO.getPassword());
			userWriteRepository.save(user);
			kafkaTemplate.send("user_topic", new UserCreatedEvent(user.getId()));
		} else {
			throw new CustomException("User with same userId present, please choose other");
		}
	}
}
