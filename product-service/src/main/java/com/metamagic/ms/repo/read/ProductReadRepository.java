package com.metamagic.ms.repo.read;

import java.util.List;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 * THIS INTERFACE IS USED FOR PRODUCT READ OPERATION
 */
public interface ProductReadRepository {
	public List<Product> findAll() throws RepositoryException;
}
