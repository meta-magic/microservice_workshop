package com.metamagic.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.User;
import com.metamagic.ms.bean.LoginCredentials;
import com.metamagic.ms.bean.LoginResponse;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.repository.read.UserReadRepository;
import com.metamagic.ms.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserReadRepository loginRepository;
	
	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> authenticate(@RequestBody LoginCredentials loginCredentials) {

		ResponseBean response = null;
		if (loginCredentials.getUserId() != null && loginCredentials.getPassword() != null) {
			User login = loginRepository.findByUserId(loginCredentials.getUserId());
			if (login != null) {
				if (loginCredentials.getUserId().equals(login.getUserId())
						&& loginCredentials.getPassword().equals(login.getPassword())) {
					LoginResponse loginResponse = new LoginResponse(tokenService.generateToken(login.getId()));
					response = new ResponseBean(true, "User authenticated successfully.", "success", loginResponse);
				} else {
					response = new ResponseBean(false, "User authentication failed.", "failure", null);
				}
			} else {
				response = new ResponseBean(false, "User not found.", "failure", null);
			}
		}
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

}
