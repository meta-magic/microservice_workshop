package com.metamagic.ms.repo.read;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.repo.GenericRepository;

/**
 * @author sagar
 * 
 * THIS REPOSITORY USED FOR READING PRODUCT 
 */
@Repository
public class ProductReadRepositoryImpl extends GenericRepository<Product> implements ProductReadRepository {

	/**
	 * THIS METHOD IS USED FOR RETIEVD ALL PRODUCT
	 * */
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {

		List<Product> working_greeting = null;
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(Product.class);
			List<Product> list = (List<Product>) query.execute();
			working_greeting = (List<Product>) pm.detachCopyAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return working_greeting;
	}

}
