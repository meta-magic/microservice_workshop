package com.metamagic.ms.repo.write;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repo.GenericRepository;

/**
 * @author sagar THIS REPO USED FOR WRITE PRODUCT
 */
@Repository
public class ProductWriteRepositoryImpl extends GenericRepository<Product> implements ProductWriteRepository {

	/**
	 * THIS METHOD IS USED FOR SAVE PRODUCT
	 * @throws RepositoryException 
	 */
	public void save(Product products) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			pm.setDetachAllOnCommit(true);
			pm.makePersistent(products);
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}
}
