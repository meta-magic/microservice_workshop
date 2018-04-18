package com.metamagic.ms.repository.read;

import com.metamagic.ms.entity.User;

/**
 * @author sagar
 * 
 * THIS REPOSITORY FOR READ OPERATION OF USER
 */
public interface UserReadRepository {

	public User findByUserId(String loginId);
}
