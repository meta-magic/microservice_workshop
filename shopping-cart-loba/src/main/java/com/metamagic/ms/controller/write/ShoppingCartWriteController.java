package com.metamagic.ms.controller.write;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
import com.metamagic.ms.controller.BaseComponent;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author sagar
 * THIS CONTROLLER USED FOR WRITE SHOPPING CART OPERATION
 */
@RestController
@RequestMapping("/shoppingcart/write")
public class ShoppingCartWriteController extends BaseComponent {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> create(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/create",
				HttpMethod.POST, httpEntity, ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/additem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> addItem(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/additem",
				HttpMethod.POST, httpEntity, ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/removeitem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> removeItem(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/removeitem",
				HttpMethod.POST, httpEntity, ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "writefallback")
	@RequestMapping(value = "/placeorder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> placeorder(@RequestBody Object payload, HttpServletRequest request) {
		HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange("http://shoppingcartservice/shoppingcart/write/placeorder",
				HttpMethod.POST, httpEntity, ResponseBean.class);
		return response;
	}

	public ResponseEntity<ResponseBean> writefallback(@RequestBody Object payload, HttpServletRequest request) {
		ResponseBean response = new ResponseBean(false,
				"Enable to connect to requested Shopping Cart Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

}
