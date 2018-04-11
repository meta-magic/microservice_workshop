package com.metamagic.ms;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
public class Config {
	
	@Bean
	public RequestValidateFilter requestValidateFilter() {
		return new RequestValidateFilter();
	}


}
