package com.metamagic.ms.service;

import java.util.Arrays;

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

/**
 * @author sagar
 * THIS SERVICE HANDLE REQUEST FROM CONTROLLER
 * 1.HANDLE REQUEST AND SEND TO SERVICE 
 * 2.FALLBACK MECHANISM 
 * 
 */
@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "authenticateFallBack")
	public ResponseEntity<ResponseBean> authenticate(@RequestBody Object object){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(object, headers);
		ResponseEntity response = this.restTemplate.exchange("http://authservice/auth/authenticate", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}
	
	public ResponseEntity<ResponseBean> authenticateFallBack(Object object, Throwable t){
		ResponseBean response = new ResponseBean(false, "Unable to connect to requested Auth Service, please try after some time", "error", t.getMessage());
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
