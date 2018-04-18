package com.metamagic.ms.listener;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.metamagic.ms.aggregate.Items;
import com.metamagic.ms.entity.ItemDocument;
import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.events.integration.OrderCompletedEvent;
import com.metamagic.ms.events.integration.OrderPlacedEvent;
import com.metamagic.ms.service.write.OrderWriteService;

/**
 * @author sagar
 * 
 * ORDER LISTNER 
 */
@Component
public class OrderEventListener {

	@Autowired
	private OrderWriteService orderWriteService;

	@Autowired
	private KafkaTemplate<String, OrderCompletedEvent> kafkaTemplate;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "test_topic")
	public void receive(OrderPlacedEvent orderPlacedEvent)
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println(orderPlacedEvent);
		latch.countDown();

		double total = 0.0;
		if (orderPlacedEvent.getItems() != null) {
			total = orderPlacedEvent.getItems().stream().mapToDouble(o -> o.getPrice()).sum();
		}
		Set<ItemDocument> documents = new HashSet<>();
		for (Iterator<Items> iterator = orderPlacedEvent.getItems().iterator(); iterator.hasNext();) {
			Items items = (Items) iterator.next();
			ItemDocument document = new ItemDocument();
			if (items.getItemId() != null && items.getName() != null) {
				document.setItemId(items.getItemId());
				document.setName(items.getName());
				document.setPrice(items.getPrice());
				document.setQuantity(items.getQuantity());
			}
			documents.add(document);
		}

		OrderDocument order = new OrderDocument(orderPlacedEvent.getCartId(), orderPlacedEvent.getCustomerId(),
				new Date(), documents, total, "COMPLETED");
		orderWriteService.save(order);

		kafkaTemplate.send("order_topic", new OrderCompletedEvent(orderPlacedEvent.getCustomerId()));
	}
}
