package com.metamagic.ms.controller.read;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.LoginResponse;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.dto.LoginDTO;
import com.metamagic.ms.exception.BussinessException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.service.read.AuthService;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 * 
 * THIS CONTROLLER IS USED AUTHENTICATION
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;
	
	/**
	 * THIS METHOD IS USED FOR AUTHENTICATE USER
	 * */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> authenticate(@RequestBody LoginDTO loginDTO) {
		try {
			LoginResponse loginResponse = authService.authenticate(loginDTO);
			ResponseBean response = new ResponseBean(true, "User authenticated successfully.", "success",
					loginResponse);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		} catch (RepositoryException e) {
			LOGGER.error("User not found USERID: " + loginDTO.getUserId());
			ResponseBean response = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		} catch (BussinessException e) {
			LOGGER.error("User not found USERID: " + loginDTO.getUserId());
			ResponseBean response = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		}
	}

}
