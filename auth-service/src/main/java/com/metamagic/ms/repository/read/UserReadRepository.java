package com.metamagic.ms.repository.read;

import com.metamagic.ms.bean.User;

/**
 * @author sagar
 * 
 * THIS REPOSITORY FOR READ OPERATION OF USER
 */
public interface UserReadRepository {

	public User findByUserId(String loginId);
}
