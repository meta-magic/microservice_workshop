package com.metamagic.ms.repo.read;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repo.GenericRepository;

/**
 * @author sagar
 * 
 *         THIS REPOSITORY USED FOR READING PRODUCT
 */
@Repository
public class ProductReadRepositoryImpl extends GenericRepository<Product> implements ProductReadRepository {

	/**
	 * THIS METHOD IS USED FOR RETIEVD ALL PRODUCT
	 * 
	 * @throws RepositoryException
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAll() throws RepositoryException {

		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(Product.class);
			List<Product> list = (List<Product>) query.execute();
			List<Product> products = (List<Product>) pm.detachCopyAll(list);
			return products;
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}
}
