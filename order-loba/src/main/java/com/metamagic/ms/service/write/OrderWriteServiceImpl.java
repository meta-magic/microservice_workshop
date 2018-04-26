package com.metamagic.ms.service.write;

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
 * 
 *         THIS SERVICE IS USED FOR ORDER UPDATE SHIPPING ADDRESS AND MADE
 *         PAYMENT
 */
@Service
public class OrderWriteServiceImpl implements OrderWriteService {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "addshippingaddressFallBack", commandKey = "Adding Shipping Address", groupKey = "Order Service")
	public ResponseEntity<ResponseBean> addShippingAddress(@RequestBody Object object) {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(object, headers);
		ResponseEntity response = this.restTemplate.exchange("http://orderservice/order/write/addshippingaddress",
				HttpMethod.POST, request, ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "addshippingaddressFallBack", commandKey = "Adding Payment Details", groupKey = "Order Service")
	public ResponseEntity<ResponseBean> addPaymentDetails(@RequestBody Object object) {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(object, headers);
		ResponseEntity response = this.restTemplate.exchange("http://orderservice/order/write/addpaymentdetails",
				HttpMethod.POST, request, ResponseBean.class);
		return response;
	}

	public ResponseEntity<ResponseBean> addshippingaddressFallBack(Object object, Throwable t) {
		ResponseBean response = new ResponseBean(false,
				"Unable to connect to requested Order Service, please try after some time", "error", t.getMessage());
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
