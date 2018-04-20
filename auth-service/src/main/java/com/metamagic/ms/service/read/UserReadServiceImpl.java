package com.metamagic.ms.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.entity.User;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.UserReadRepository;

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
	 * @throws RepositoryException 
	 */
	public User findByLoginId(String loginId) throws RepositoryException {
		return loginReadRepository.findByUserId(loginId);
	}
}
