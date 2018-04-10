package com.metamagic.ms.service.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.Product;
import com.metamagic.ms.repo.read.ProductReadRepository;

@Service
public class ProductReadService {

	@Autowired
	ProductReadRepository productRepository;
	
	public List<Product> getProducts(){
		List<Product> prds = productRepository.findAll();
		return prds;
	}
	
	
}
