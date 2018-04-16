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

import com.metamagic.ms.bean.Login;
import com.metamagic.ms.bean.LoginCredentials;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.repository.LoginRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private LoginRepository loginRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> authenticate(@RequestBody LoginCredentials loginCredentials) {

		ResponseBean response = null;
		if (loginCredentials.getLoginId() != null && loginCredentials.getPassword() != null) {
			Login login = loginRepository.findByLoginId(loginCredentials.getLoginId());
			if (login != null) {
				if (loginCredentials.getLoginId().equals(login.getLoginId())
						&& loginCredentials.getPassword().equals(login.getPassword())) {
					response = new ResponseBean(true, "User authenticated successfully.", "success", null);
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
