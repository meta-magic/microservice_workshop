package com.metamagic.ms.repository.write.impl;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.User;
import com.metamagic.ms.repository.common.GenericRepository;
import com.metamagic.ms.repository.write.UserWriteRepository;

/**
 * @author sagar 
 * 
 * THIS REPOSITORY IMPL USED FOR WRITE,UPDATE USER OBJECTS
 */
@Repository
public class UserWriteRepositoryImpl extends GenericRepository<User> implements UserWriteRepository {

	@Override
	public User save(User user) {
		User persistedObject = persist(user);
		return persistedObject;
	}

}
