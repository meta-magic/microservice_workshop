package com.metamagic.ms.repository.write;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar
 * 
 * THIS REPOSITORY IS USED FOR USER CART READ OPERATION
 */
@Repository
public class UserCartWriteRepositoryImpl extends GenericRepository<UserCart> implements UserCartWriteRepository {

	@Override
	public void save(UserCart userCart) {
		persist(userCart);	
    }

}
