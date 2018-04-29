package com.metamagic.ms.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.UserCartReadRepository;

/**
 * @author sagar THIS SERVICE IS USED FOR SHOPPING CART READ OPERATION
 */
@Service
public class ShoppingCartReadServiceImpl implements ShoppingCartReadService {

	@Autowired
	private UserCartReadRepository cartReadRepository;

	/**
	 * THIS METHOD IS USED FOR FINDING USER CART
	 * @throws RepositoryException 
	 */
	@Override
	public UserCart fetchcart(String id,String status) throws RepositoryException {
		UserCart userCart = cartReadRepository.findByUserIdAndActive(id, status);
		return userCart;
	}

}
