package com.metamagic.ms.service.write;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.controller.BaseComponent;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author sagar THIS SERVICE USED FOR WRITE SHOPPING CART OPERATION
 */
@Service
public class ShoppingCartWriteServiceImpl extends BaseComponent implements ShoppingCartWriteService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "writefallback",commandKey="Create Cart",groupKey="Shopping Write Service")
	public ResponseEntity<ResponseBean> create(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange(
				"http://shoppingcartservice/shoppingcart/write/create", HttpMethod.POST, httpEntity,
				ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "writefallback",commandKey="Add Item",groupKey="Shopping Write Service")
	public ResponseEntity<ResponseBean> addItem(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange(
				"http://shoppingcartservice/shoppingcart/write/additem", HttpMethod.POST, httpEntity,
				ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "writefallback",commandKey="Remove Item",groupKey="Shopping Write Service")
	public ResponseEntity<ResponseBean> removeItem(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange(
				"http://shoppingcartservice/shoppingcart/write/removeitem", HttpMethod.POST, httpEntity,
				ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "writefallback",commandKey="Place Order",groupKey="Shopping Write Service")
	public ResponseEntity<ResponseBean> placeorder(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange(
				"http://shoppingcartservice/shoppingcart/write/placeorder", HttpMethod.POST, httpEntity,
				ResponseBean.class);
		return response;
	}

	public ResponseEntity<ResponseBean> writefallback(@RequestBody Object payload, HttpServletRequest request,
			Throwable t) {
		ResponseBean response = new ResponseBean(false,
				"Enable to connect to requested Shopping Cart Service, please try after some time", "error",
				t.getMessage());
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

}
