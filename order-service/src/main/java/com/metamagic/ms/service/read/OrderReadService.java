package com.metamagic.ms.service.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.Order;
import com.metamagic.ms.repository.read.OrderReadRepository;

@Service
public class OrderReadService {

	@Autowired
	private OrderReadRepository orderReadRepository;

	public List<Order> findAll() {

		List<Order> ordersList = orderReadRepository.findAll();

		return ordersList;
	}

}
