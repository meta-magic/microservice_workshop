package com.metamagic.ms.controller.read;

import javax.servlet.http.HttpServletRequest;

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
import com.metamagic.ms.controller.BaseComponent;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author sagar
 * THIS CONTROLLER HANDLE REQUEST FROM UI
 * 1.HANDLE REQUEST AND SEND TO SERVICE 
 * 2.FALLBACK MECHANISM 
 * 
 */
@RestController
@RequestMapping("/order/query")
public class OrderReadController extends BaseComponent {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "findAllFallBack")
	@RequestMapping(value = "/orderhistory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> findAll(HttpServletRequest request) {
		org.springframework.http.HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange("http://orderservice/order/query/orderhistory",
				HttpMethod.GET, httpEntity, ResponseBean.class);
		return response;
	}

	public ResponseEntity<ResponseBean> findAllFallBack(HttpServletRequest request) {
		ResponseBean response = new ResponseBean(false,
				"Enable to connect to requested Product Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
