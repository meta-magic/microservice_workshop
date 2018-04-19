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
import com.metamagic.ms.service.read.AuthService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> authenticate(@RequestBody LoginDTO loginDTO) {
		LOGGER.info("User login started USERID: " + loginDTO.getUserId());
		ResponseBean response = null;
		LoginResponse loginResponse = authService.authenticate(loginDTO);
		if (loginResponse != null) {
			response = new ResponseBean(true, "User authenticated successfully.", "success", loginResponse);
			LOGGER.info("User authenticated successfully. USERID: " + loginDTO.getUserId());

		} else {
			LOGGER.error("User not found USERID: " + loginDTO.getUserId());
			response = new ResponseBean(false, "User not found.", "failure", null);
		}
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

}
