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
import com.metamagic.ms.entity.User;
import com.metamagic.ms.repository.read.UserReadRepository;
import com.metamagic.ms.service.TokenService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserReadRepository loginRepository;

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> authenticate(@RequestBody LoginDTO loginCredentials) {
		LOGGER.info("User login started USERID: " + loginCredentials.getUserId());
		ResponseBean response = null;
		if (loginCredentials.getUserId() != null && loginCredentials.getPassword() != null) {
			User login = loginRepository.findByUserId(loginCredentials.getUserId());
			if (login != null) {
				if (loginCredentials.getUserId().equals(login.getUserId())
						&& loginCredentials.getPassword().equals(login.getPassword())) {
					LoginResponse loginResponse = new LoginResponse(tokenService.generateToken(login.getId()));
					response = new ResponseBean(true, "User authenticated successfully.", "success", loginResponse);
					LOGGER.info("User authenticated successfully. USERID: " + loginCredentials.getUserId());
				} else {
					response = new ResponseBean(false, "User authentication failed.", "failure", null);
				}
			} else {
				LOGGER.error("User not found USERID: " + loginCredentials.getUserId());
				response = new ResponseBean(false, "User not found.", "failure", null);
			}
		}
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

}
