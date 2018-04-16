package com.metamagic.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metamagic.ms.bean.Login;
import com.metamagic.ms.repository.LoginRepository;

@Component
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public Login findByLoginId(String loginId) {
		return loginRepository.findByLoginId(loginId);
	}
}
