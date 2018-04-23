package com.metamagic.ms;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
/**
 * @author sagar
 * THIS CONFIG CLASS USED FOR FOLLWING THINGS
 * 1.EUREKA
 * 2.CIRCUITBREAKER
 * 3.LOAD BALANCER
 * 4.THREAD POOL
 */
@Configuration
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class Config {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@LoadBalanced
	@Bean
	public AsyncRestTemplate asyncRestTemplate(@LoadBalanced RestTemplate restTemplate) {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setTaskExecutor(threadPoolTaskScheduler());
		return new AsyncRestTemplate(requestFactory, restTemplate);
	}

	@Bean(destroyMethod = "shutdown")
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		threadPoolTaskScheduler.initialize();
		return threadPoolTaskScheduler;
	}

}
