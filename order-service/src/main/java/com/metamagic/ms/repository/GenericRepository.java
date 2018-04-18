package com.metamagic.ms.repository;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sagar
 * 
 * THIS ABSTRACT CLASS USED FOR COMMON OPERATION OF REPO LIKE PERSIST,UPDATE,DELETE
 * 
 * @param <T>
 */
public abstract class GenericRepository<T> {

	@Autowired
	PersistenceManagerFactory pmf;

	/**
	 * THIS METHOD IS USED TO CREATE INSTACE OF PERSISTENCEMANAGERFACTORY
	 */
	public PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}

	/**
	 * THIS METHOD IS USED FOR PERSIST T TYPE OF OBJECT IN DATABASE
	 * */
	public T persist(T t) {
		PersistenceManager pm = pm();
		T storedObject = pm.makePersistent(t);
		T detachedObject = pm.detachCopy(storedObject);
		pm.close();
		return detachedObject;
	}
}
