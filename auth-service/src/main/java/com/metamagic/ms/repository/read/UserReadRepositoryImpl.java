package com.metamagic.ms.repository.read;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.User;

@Repository
public class UserReadRepositoryImpl implements UserReadRepository {

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}
	
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
