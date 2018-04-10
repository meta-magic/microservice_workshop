package com.metamagic.ms.controller.write;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.metamagic.ms.bean.ResponseBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController 
@RequestMapping("/shoppingcart/write")
public class ShoppingCartWriteController {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/create", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> create(@RequestBody Object payload){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(payload, headers);
		ResponseEntity response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/create", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}
	
	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/additem", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> addItem(@RequestBody Object payload){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(payload, headers);
		ResponseEntity response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/additem", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/removeitem", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> removeItem(@RequestBody Object payload){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(payload, headers);
		ResponseEntity response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/removeitem", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}
	
	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/placeorder", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> placeorder(@RequestBody Object payload){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(payload, headers);
		ResponseEntity response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/placeorder", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}	
	
	public ResponseEntity<ResponseBean> writefallback(Object payload){
		ResponseBean response = new ResponseBean(false, "Enable to connect to requested Shopping Cart Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

	

	
	
}
