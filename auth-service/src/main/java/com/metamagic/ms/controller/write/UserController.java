package com.metamagic.ms.controller.write;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.dto.UserDTO;
import com.metamagic.ms.exception.CustomException;
import com.metamagic.ms.service.write.UserWriteService;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 * 
 * THIS CONTROLLER USED FOR USER REQUEST 
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserWriteService userWriteService;
	
	/**
	 * THIS METHOD USED FOR CREATE USER
	 * */
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> createUser(@RequestBody UserDTO userDTO){
		ResponseBean responseBean = null;
		LOGGER.info("User creation started."+userDTO.getFirstName());
		try {
			userWriteService.createUser(userDTO);
			responseBean = new ResponseBean(true, "User saved successfully.", "success", null);
			LOGGER.info("User created successfully."+userDTO.getFirstName());
		} catch (CustomException e) {
			responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
			LOGGER.error("User creation failed:"+ e.getMessage());
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
}
