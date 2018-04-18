package com.metamagic.ms.controller.write;

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

/**
 * @author sagar
 * 
 * THIS CONTROLLER USED FOR USER REQUEST 
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserWriteService userWriteService;
	
	/**
	 * THIS METHOD USED FOR CREATE USER
	 * */
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> createUser(@RequestBody UserDTO userDTO){
		ResponseBean responseBean = null;
		try {
			userWriteService.createUser(userDTO);
			responseBean = new ResponseBean(true, "User saved successfully.", "success", null);
		} catch (CustomException e) {
			responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
}
