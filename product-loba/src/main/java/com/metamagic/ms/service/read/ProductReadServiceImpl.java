package com.metamagic.ms.service.read;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metamagic.ms.bean.ResponseBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author sagar THIS SERVICE IS USED FOR PRODUCT READ OPERATAION
 */

@Service
public class ProductReadServiceImpl implements ProductReadService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "findAllFallBack")
	public ResponseEntity<ResponseBean> findAll() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity response = this.restTemplate.exchange("http://productservice/product/query/findall",
				HttpMethod.GET, request, ResponseBean.class);
		return response;
	}

	public ResponseEntity<ResponseBean> findAllFallBack(Throwable t) {
		ResponseBean response = new ResponseBean(false,
				"Enable to connect to requested Product Service, please try after some time", "error", t.getMessage());
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}
