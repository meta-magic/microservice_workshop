package com.metamagic.ms.service.write;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.controller.BaseComponent;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author sagar
 * 
 * THIS SERVICE USE FOR WRITE PRODUCT WRITE OPERATION
 */
@Service
public class ProductWriteServiceImpl extends BaseComponent implements ProductWriteService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	@HystrixCommand(fallbackMethod = "saveFallBack")
	public ResponseEntity<ResponseBean> save(@RequestBody Object payload, HttpServletRequest request) {
		org.springframework.http.HttpHeaders headers = this.createHeaders(request);
		HttpEntity<?> httpEntity = new HttpEntity<>(payload, headers);
		ResponseEntity<ResponseBean> response = this.restTemplate.exchange("http://productservice/product/write/save",
				HttpMethod.POST, httpEntity, ResponseBean.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "findAllFallBack2")
	public DeferredResult<ResponseEntity<ResponseBean>> findAll() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);
		DeferredResult<ResponseEntity<ResponseBean>> deferredResult = new DeferredResult<>();
		ListenableFuture<ResponseEntity<ResponseBean>> response = this.asyncRestTemplate
				.exchange("http://productservice/product/all", HttpMethod.GET, request, ResponseBean.class);

		response.addCallback(new ListenableFutureCallback<ResponseEntity<ResponseBean>>() {
			@Override
			public void onSuccess(ResponseEntity<ResponseBean> response) {
				deferredResult.setResult(response);
			}

			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				ResponseBean response = new ResponseBean(false, throwable.getMessage(), null,
						HttpStatus.INTERNAL_SERVER_ERROR.value());
				deferredResult.setErrorResult(new ResponseEntity<ResponseBean>(response, HttpStatus.OK));
			}
		});

		return deferredResult;
	}

	public ResponseEntity<ResponseBean> saveFallBack(Object payload, HttpServletRequest request) {
		ResponseBean response = new ResponseBean(false,
				"Enable to connect to requested Product Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

	public DeferredResult<ResponseEntity<ResponseBean>> findAllFallBack2() {
		DeferredResult<ResponseEntity<ResponseBean>> deferredResult = new DeferredResult<>();
		ResponseBean response = new ResponseBean(false,
				"Enable to connect to requested Product Service, please try after some time", "error", null);
		deferredResult.setErrorResult(new ResponseEntity<>(response, HttpStatus.OK));
		return deferredResult;
	}

}
