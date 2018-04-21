package com.metamagic.ms.repository;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 *         THIS ABSTRACT CLASS USED FOR COMMON OPERATION OF REPO LIKE
 *         PERSIST,UPDATE,DELETE
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
	 * @throws RepositoryException 
	 */
	public T persist(T t) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			T storedObject = pm.makePersistent(t);
			T detachedObject = pm.detachCopy(storedObject);
			return detachedObject;
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}
}
