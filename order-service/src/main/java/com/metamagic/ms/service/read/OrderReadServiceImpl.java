package com.metamagic.ms.service.read;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.dto.OrderDTO;
import com.metamagic.ms.dto.OrderItemDTO;
import com.metamagic.ms.entity.LineItem;
import com.metamagic.ms.entity.Order;
import com.metamagic.ms.entity.Order.Status;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.OrderReadRepository;

/**
 * @author sagar THIS ORDERSERVICE ISED FOR READ OPERATION OF ORDER
 */
@Service
public class OrderReadServiceImpl implements OrderReadService {

	@Autowired
	private OrderReadRepository orderReadRepository;

	// THIS METHOD IS RETURN ORDERS BY USERID
	public List<Order> findAll(String userId) throws RepositoryException {
		List<Order> ordersList = orderReadRepository.findAll(userId);
		return ordersList;
	}

	// THIS METHOD RETURN ORDER DTO WHICH IS IN PREPAREING STATE
	public OrderDTO getOrderDetails(String userId) throws RepositoryException {
		Order document = orderReadRepository.findByUserIdAndStatus(userId, Status.PREPARING);
		OrderDTO orderDTO = new OrderDTO();
		List<OrderItemDTO> itemDTOs = new ArrayList<>();
		for (Iterator<LineItem> iterator = document.getItems().iterator(); iterator.hasNext();) {
			LineItem itemDocument = (LineItem) iterator.next();
			itemDTOs.add(
					new OrderItemDTO(itemDocument.getItemName(), itemDocument.getPrice(), itemDocument.getQuantity()));
		}
		orderDTO.setItemDTOs(itemDTOs);
		orderDTO.setOrderId(document.getOrderId());
		orderDTO.setTotal(document.getTotal());
		return orderDTO;
	}

	// THIS METHOD RETRUN ORDER BY ID
	public Order findOrderById(String orderId) throws RepositoryException {
		Order orderDocument = orderReadRepository.findByOrderId(orderId);
		return orderDocument;
	}
}
