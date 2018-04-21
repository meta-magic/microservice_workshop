package com.metamagic.ms.repo.write;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar THIS REPOSITORY USED FOR WRITE PRODUCT
 */
public interface ProductWriteRepository {
	public void save(Product products) throws RepositoryException;
}
