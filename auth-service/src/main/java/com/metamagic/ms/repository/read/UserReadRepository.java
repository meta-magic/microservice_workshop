package com.metamagic.ms.repository.read;

import com.metamagic.ms.entity.User;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 * THIS REPOSITORY FOR READ OPERATION OF USER
 */
public interface UserReadRepository {

	public User findByUserId(String loginId) throws RepositoryException;
}
