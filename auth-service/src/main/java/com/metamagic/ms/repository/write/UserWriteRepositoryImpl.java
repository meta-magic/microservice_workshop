package com.metamagic.ms.repository.write;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.User;

@Repository
public class UserWriteRepositoryImpl implements UserWriteRepository {

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}
	
	@Override
	public User save(User user) {
		PersistenceManager pm = pm();
		User login2 = pm.makePersistent(user);
		pm.close();
		return login2;
	}

}
