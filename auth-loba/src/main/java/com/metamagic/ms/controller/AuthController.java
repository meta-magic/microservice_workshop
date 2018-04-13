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

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "authenticateFallBack")
	@RequestMapping(value = "/authenticate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> authenticate(@RequestBody Object object){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(object, headers);
		ResponseEntity response = this.restTemplate.exchange("http://authservice/auth/authenticate", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}
	
	public ResponseEntity<ResponseBean> authenticateFallBack(Object object){
		ResponseBean response = new ResponseBean(false, "Unable to connect to requested Auth Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
