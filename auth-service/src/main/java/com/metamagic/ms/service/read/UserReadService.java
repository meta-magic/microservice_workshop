package com.metamagic.ms.service.read;

import com.metamagic.ms.entity.User;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 * THIS INTERFACE USED FOR USER COMMON OPERATION
 */
public interface UserReadService {
	public User findByLoginId(String loginId) throws RepositoryException;
}
