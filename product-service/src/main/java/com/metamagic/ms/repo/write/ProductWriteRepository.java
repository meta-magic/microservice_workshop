package com.metamagic.ms.repo.write;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.Product;

@Repository
public class ProductWriteRepository {

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}
	
	public void save(Product products) {
		PersistenceManager pm = pm();
		pm.setDetachAllOnCommit(true);
		pm.makePersistent(products);
		pm.close();
	}
}
