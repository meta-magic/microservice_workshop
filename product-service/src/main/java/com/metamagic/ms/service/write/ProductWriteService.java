package com.metamagic.ms.service.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.Product;
import com.metamagic.ms.repo.write.ProductWriteRepository;

@Service
public class ProductWriteService {

	@Autowired
	ProductWriteRepository productRepository;
	
	public void save(Product product)
	{
		productRepository.save(product);
	}
}
