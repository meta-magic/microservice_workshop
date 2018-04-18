package com.metamagic.ms.controller.read;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.metamagic.ms.bean.ResponseBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author sagar
 * THIS CLASS USED FOR PRODUCT READ
 */
@RestController
@RequestMapping("/product/query")
public class ProductReadController {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "findAllFallBack")
	@RequestMapping(value = "/findall", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> findAll(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity response = this.restTemplate.exchange("http://productservice/product/query/findall", HttpMethod.GET,request,ResponseBean.class);
		return response;
	}
	
	public ResponseEntity<ResponseBean> findAllFallBack(){
		ResponseBean response = new ResponseBean(false, "Enable to connect to requested Product Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
