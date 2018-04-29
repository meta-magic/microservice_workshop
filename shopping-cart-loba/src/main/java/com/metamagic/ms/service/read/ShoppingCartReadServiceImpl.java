package com.metamagic.ms.service.read;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
 * @author sagar 
 * 
 * THIS SERVICE IS USED FOR READING SHOPPING CART OPERTAIONS
 */

@Service
public class ShoppingCartReadServiceImpl extends BaseComponent implements ShoppingCartReadService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fecthcartFallBack",commandKey="Fetch All Cart",groupKey="Shopping Read Service")
	public ResponseEntity<ResponseBean> fecthcart(@RequestBody Object payload, HttpServletRequest request) {
		org.springframework.http.HttpHeaders headers = this.createHeaders(request);
		
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange(
				"http://shoppingcartservice/shoppingcart/read/fecthcart", HttpMethod.POST, httpEntity,
				ResponseBean.class);
		return response;
	}

	public ResponseEntity<ResponseBean> fecthcartFallBack(Object payload, HttpServletRequest request) {
		ResponseBean response = new ResponseBean(false,
				"Enable to connect to requested Shopping cart service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
