package com.metamagic.ms.service.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.entity.Product;
import com.metamagic.ms.repo.read.ProductReadRepository;

/**
 * @author sagar
 * 
 * THIS SERVICE IS USED FOR READ OF PRODUCT 
 */
@Service
public class ProductReadServiceImpl implements ProductReadService {

	@Autowired
	ProductReadRepository productRepository;
 
	/**
	 * THIS METHOD IS USED FOR GET ALL PRODUCT LIST
	 * */
	public List<Product> getProducts() {
		List<Product> prds = productRepository.findAll();
		return prds;
	}

}
