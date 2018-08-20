package com.metamagic.ms.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.AuthService;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar THIS CONTROLLER HANDLE REQUEST FROM UI 1.HANDLE REQUEST AND
 *         SEND TO SERVICE 2.FALLBACK MECHANISM
 * 
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> authenticate(@RequestBody Object object) {
		
		return authService.authenticate(object);
	}
}
