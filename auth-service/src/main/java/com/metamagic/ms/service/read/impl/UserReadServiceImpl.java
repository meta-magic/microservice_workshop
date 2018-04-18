package com.metamagic.ms.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.User;
import com.metamagic.ms.repository.read.UserReadRepository;
import com.metamagic.ms.service.read.UserReadService;

/**
 * @author sagar
 *
 * THIS USER READ SERVICE IS USED FOR USER OPERATIONS LIKE FINDALL,FINDBYID 
 */
@Service
public class UserReadServiceImpl implements UserReadService {

	@Autowired
	private UserReadRepository loginReadRepository;

	/**
	 * THIS METHOD IS FIND THE USER BY LOGINID
	 */
	public User findByLoginId(String loginId) {
		return loginReadRepository.findByUserId(loginId);
	}
}
