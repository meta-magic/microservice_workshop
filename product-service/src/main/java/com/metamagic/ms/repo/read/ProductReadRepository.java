package com.metamagic.ms.repo.read;

import java.util.List;

import com.metamagic.ms.entity.Product;

/**
 * @author sagar
 *
 */
public interface ProductReadRepository {
	public List<Product> findAll();
}
