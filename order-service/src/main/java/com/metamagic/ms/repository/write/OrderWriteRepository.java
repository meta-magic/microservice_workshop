package com.metamagic.ms.repository.write;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.metamagic.ms.bean.Order;

@Repository
public interface OrderWriteRepository extends MongoRepository<Order, String>{

}
