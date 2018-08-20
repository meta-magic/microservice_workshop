package com.metamagic.ms.service;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.metamagic.ms.bean.ResponseBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 * 
 *         THIS SERVICE HANDLE REQUEST FROM CONTROLLER 1.HANDLE REQUEST AND SEND
 *         TO SERVICE 2.FALLBACK MECHANISM
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "createFallBack",commandKey="Create User",groupKey="User Service")
	public ResponseEntity<ResponseBean> create(@RequestBody Object object) {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(object, headers);
		ResponseEntity response = this.restTemplate.exchange("http://userauthservice/user/create", HttpMethod.POST, request,
				ResponseBean.class);
		return response;
	}

	public ResponseEntity<ResponseBean> createFallBack(Object object, Throwable t) {
		log.error("Unable to connect to requested Auth Service, please try after some time");
		ResponseBean response = new ResponseBean(false,
				"Unable to connect to requested Auth Service, please try after some time", "error", t.getMessage());
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
