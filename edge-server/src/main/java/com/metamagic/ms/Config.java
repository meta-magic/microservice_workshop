package com.metamagic.ms;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.metamagic.ms.filter.InvalidResourceFilter;
import com.metamagic.ms.filter.RequestLogValidateFilter;
import com.metamagic.ms.filter.TokenFilter;

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
public class Config {
	
	@Bean
	@RefreshScope
	public RequestLogValidateFilter requestValidateFilter() {
		return new RequestLogValidateFilter();
	}

	@Bean
	@RefreshScope
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}
	
	@Bean
	@RefreshScope
	public InvalidResourceFilter invalidResourceFilter(){
		return new InvalidResourceFilter();
	}
}
