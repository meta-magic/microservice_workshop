package com.metamagic.ms.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.documents.UserCart;
import com.metamagic.ms.repository.UserCartRepository;

@Service
public class ShoppingCartReadService {

	@Autowired
	private UserCartRepository userCartRepository;
	
	public UserCart fetchcart(String id){
		
		UserCart userCart = userCartRepository.findByUserId(id);
		
		return userCart;
	}
	
}
