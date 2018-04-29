package com.metamagic.ms.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.aggregate.Items;
import com.metamagic.ms.entity.LineItem;
import com.metamagic.ms.entity.Order;
import com.metamagic.ms.entity.Order.Status;
import com.metamagic.ms.events.integration.OrderPlacedEvent;
import com.metamagic.ms.exception.BussinessException;
import com.metamagic.ms.exception.IllegalArgumentCustomException;
import com.metamagic.ms.exception.InvalidDataException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.OrderReadRepository;
import com.metamagic.ms.service.write.OrderWriteService;

/**
 * @author sagar THIS BUSINESS LOGIC IS USED FOR ORDER
 */
@Service
public class OrderBusinessLogicServiceImpl implements OrderBusinessLogicService {

	@Autowired
	private OrderWriteService orderWriteService;

	@Autowired
	private OrderReadRepository orderReadRepository;

	/**
	 * THIS PRIVATE METHOD IS USED FOR CHECK ORDER STATUS AND UPDATE ITEMS IF STATUS
	 * IS PREPARING
	 * 
	 * @throws RepositoryException
	 * @throws InvalidDataException
	 */
	public void save(OrderPlacedEvent orderPlacedEvent)
			throws BussinessException, IllegalArgumentCustomException, RepositoryException, InvalidDataException {
		Order doOrderDocument = orderReadRepository.findByUserIdAndStatus(orderPlacedEvent.getUserId(),
				Status.PREPARING);

		// HERE CART ITEM EMPTY
		if (doOrderDocument != null) {
			doOrderDocument.initCart();
		}

		double total = 0.0;
		if (orderPlacedEvent.getItems() != null) {
			total = orderPlacedEvent.getItems().stream().mapToDouble(o -> o.getPrice()).sum();
		}
		Set<LineItem> documents = new HashSet<>();
		for (Iterator<Items> iterator = orderPlacedEvent.getItems().iterator(); iterator.hasNext();) {
			Items items = (Items) iterator.next();

			if (items.getItemId() != null && items.getName() != null) {
				try {
					LineItem document = new LineItem(items.getItemId(), items.getName(), items.getPrice(),
							items.getQuantity(), doOrderDocument);
					if (doOrderDocument != null) {
						doOrderDocument.addLineItem(items.getItemId(), items.getName(), items.getPrice(),
								items.getQuantity());
					}
					documents.add(document);
				} catch (IllegalArgumentCustomException e) {
					throw new BussinessException(e.getMessage());
				}
			}
		}
		if (doOrderDocument == null) {
			Order order = new Order(orderPlacedEvent.getCartId(), orderPlacedEvent.getUserId(),
					new Date(), documents, total, Status.PREPARING);
			orderWriteService.save(order);
		} else {
			doOrderDocument.moneytoryValue();
			doOrderDocument.updateOrderDate();
			orderWriteService.save(doOrderDocument);
		}

	}
}
