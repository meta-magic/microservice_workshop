package com.metamagic.ms.service.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.Order;
import com.metamagic.ms.repository.write.OrderWriteRepository;

@Service
public class OrderWriteService {

	@Autowired
	private OrderWriteRepository orderWriteRepository;

	public Order save(Order order) {

		Order order2 = orderWriteRepository.save(order);

		return order2;
	}

}
