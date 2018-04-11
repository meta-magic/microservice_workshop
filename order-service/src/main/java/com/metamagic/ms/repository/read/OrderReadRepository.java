package com.metamagic.ms.repository.read;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.Order;

@Repository
public interface OrderReadRepository extends MongoRepository<Order, String>{

}
