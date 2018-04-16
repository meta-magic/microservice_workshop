package com.metamagic.ms.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metamagic.ms.bean.User;
import com.metamagic.ms.repository.read.UserReadRepository;

@Component
public class UserReadService {

	@Autowired
	private UserReadRepository loginReadRepository;
	
	public User findByLoginId(String loginId) {
		return loginReadRepository.findByUserId(loginId);
	}
}
