package com.metamagic.ms.repository.write;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.User;
import com.metamagic.ms.repository.GenericRepository;

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
