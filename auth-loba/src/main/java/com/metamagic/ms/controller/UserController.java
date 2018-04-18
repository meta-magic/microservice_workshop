package com.metamagic.ms.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.metamagic.ms.bean.ResponseBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * @author sagar
 * THIS CONTROLLER HANDLE REQUEST FROM UI
 * 1.HANDLE REQUEST AND SEND TO SERVICE 
 * 2.FALLBACK MECHANISM 
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "createFallBack")
	@RequestMapping(value = "/create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> create(@RequestBody Object object){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(object, headers);
		ResponseEntity response = this.restTemplate.exchange("http://authservice/user/create", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}
	
	public ResponseEntity<ResponseBean> createFallBack(Object object){
		ResponseBean response = new ResponseBean(false, "Unable to connect to requested Auth Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
