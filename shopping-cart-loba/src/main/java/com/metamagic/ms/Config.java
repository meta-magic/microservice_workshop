package com.metamagic.ms;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Config {

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	


}
