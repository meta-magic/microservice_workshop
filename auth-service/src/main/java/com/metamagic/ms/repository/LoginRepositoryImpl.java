package com.metamagic.ms.repository;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.Login;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}
	
	@Override
	public Login findByLoginId(String loginId) {
		PersistenceManager pm = pm();
		Login login = null;
		try {
			Query query = pm.newQuery(Login.class);
			query.setFilter("loginId == :loginId");
			query.setUnique(true);
			login = (Login) query.execute(loginId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return login;
	}

}
