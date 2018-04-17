package com.metamagic.ms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.ms.documents.UserCart;

@Repository
public interface UserCartRepository extends MongoRepository<UserCart, String>{

	public UserCart findByUserIdAndActive(String userId, boolean active);
}
