package com.metamagic.ms;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageReceiver {


	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }

	  @KafkaListener(topics = "test_topic")
	  public void receive(String msg) {
	    System.out.println(msg);
	    latch.countDown();
	  }
}
