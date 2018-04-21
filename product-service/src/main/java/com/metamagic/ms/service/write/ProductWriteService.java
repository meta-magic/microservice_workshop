package com.metamagic.ms.service.write;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 * THIS INTERFACE USED FOR PRODUCT WRTIE OPERATION
 */
public interface ProductWriteService {
	public void save(Product product) throws RepositoryException ;
}
