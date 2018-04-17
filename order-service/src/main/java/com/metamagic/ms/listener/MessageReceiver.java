package com.metamagic.ms.listener;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.metamagic.ms.bean.Order;
import com.metamagic.ms.events.OrderCompletedEvent;
import com.metamagic.ms.events.OrderPlacedEvent;
import com.metamagic.ms.service.write.OrderWriteService;

@Component
public class MessageReceiver {

	  @Autowired
	  private OrderWriteService orderWriteService;
	  
	  @Autowired
	  private KafkaTemplate<String, OrderCompletedEvent> kafkaTemplate;
	
	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }

	  @KafkaListener(topics = "test_topic")
	  public void receive(OrderPlacedEvent orderPlacedEvent) throws JsonParseException, JsonMappingException, IOException {
	    System.out.println(orderPlacedEvent);
	    latch.countDown();
	    
	    double total = 0.0;
	    if(orderPlacedEvent.getItems()!=null) {
	    	total = orderPlacedEvent.getItems().stream().mapToDouble(o -> o.getPrice()).sum();
	    }
	    Order order = new Order(orderPlacedEvent.getCartId(), orderPlacedEvent.getCustomerId(), new Date(), orderPlacedEvent.getItems(), total, "COMPLETED");
	    orderWriteService.save(order);
	    
	    kafkaTemplate.send("order_topic", new OrderCompletedEvent(orderPlacedEvent.getCustomerId()));
	  }
}
