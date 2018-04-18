package com.metamagic.ms.service.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.repository.write.OrderWriteRepository;

/**
 * @author sagar
 * THIS ORDER SERVICE USED FOR WRITE THE ORDER
 */
@Service
public class OrderWriteServiceImpl implements OrderWriteService {

	@Autowired
	private OrderWriteRepository orderWriteRepository;

	public OrderDocument save(OrderDocument order) {

		OrderDocument order2 = orderWriteRepository.save(order);

		return order2;
	}

}
