package com.metamagic.ms.repository.read.impl;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.User;
import com.metamagic.ms.repository.common.GenericRepository;
import com.metamagic.ms.repository.read.UserReadRepository;

/**
 * @author sagar
 * 
 * THIS REPOSITORY IMPLMENTATION IS FOR USER READ OPERAIONS
 */
@Repository
public class UserReadRepositoryImpl extends GenericRepository<User> implements UserReadRepository {

	@Override
	public User findByUserId(String userId) {
		PersistenceManager pm = pm();
		User user = null;
		try {
			Query query = pm.newQuery(User.class);
			query.setFilter("userId == :userId");
			query.setUnique(true);
			user = (User) query.execute(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return user;
	}

}
