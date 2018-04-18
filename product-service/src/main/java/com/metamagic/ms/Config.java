package com.metamagic.ms;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sagar
 * CONFIG OF DATABASE
 */
@Configuration
@EnableEurekaClient
public class Config {

	@Bean
	public PersistenceManagerFactory persistenceManagerFactory(){
		return JDOHelper.getPersistenceManagerFactory("PersistenceUnit");
	}
}
