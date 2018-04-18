package com.metamagic.ms.repo.write;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.repo.GenericRepository;

/**
 * @author sagar
 * THIS REPO USED FOR WRITE PRODUCT
 */
@Repository
public class ProductWriteRepositoryImpl extends GenericRepository<Product> implements ProductWriteRepository {

	/**
	 * THIS METHOD IS USED FOR SAVE PRODUCT
	 * */
	public void save(Product products) {
		PersistenceManager pm = pm();
		pm.setDetachAllOnCommit(true);
		pm.makePersistent(products);
		pm.close();
	}
}
