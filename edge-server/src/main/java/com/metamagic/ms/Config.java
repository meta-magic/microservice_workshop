package com.metamagic.ms;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.metamagic.ms.filter.RequestValidateFilter;
import com.metamagic.ms.filter.TokenFilter;

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
public class Config {
	
	@Bean
	public RequestValidateFilter requestValidateFilter() {
		return new RequestValidateFilter();
	}

	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}
}
