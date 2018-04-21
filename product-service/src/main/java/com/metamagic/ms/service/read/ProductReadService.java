package com.metamagic.ms.service.read;

import java.util.List;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 *         THIS SERVICE INTEFACE IS USED FOR READ PRODUCT
 */
public interface ProductReadService {

	public List<Product> getProducts() throws RepositoryException;
}
