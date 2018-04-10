package com.metamagic.ms;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

import com.metamagic.ms.events.OrderPlacedEvent;

public class MessageReceiver {


	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }

	  @KafkaListener(topics = "test_topic")
	  public void receive(OrderPlacedEvent msg) {
	    System.out.println(msg);
	    latch.countDown();
	  }
}
