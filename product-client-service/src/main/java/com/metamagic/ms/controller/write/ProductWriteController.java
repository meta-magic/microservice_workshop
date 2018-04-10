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
@RequestMapping("/product/write")
public class ProductWriteController {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "saveFallBack")
	@RequestMapping(value = "/save", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> save(@RequestBody Object payload){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(payload, headers);
		ResponseEntity response = this.restTemplate.exchange("http://productservice/product/write/save", HttpMethod.POST,request,ResponseBean.class);
		return response;
	}
	
	public ResponseEntity<ResponseBean> saveFallBack(Object payload){
		ResponseBean response = new ResponseBean(false, "Enable to connect to requested Product Service, please try after some time", "error", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

	

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;
	

	@HystrixCommand(fallbackMethod = "findAllFallBack2")
	@RequestMapping(value="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<ResponseBean>> all(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);
		DeferredResult<ResponseEntity<ResponseBean>> deferredResult = new DeferredResult<>();
		ListenableFuture<ResponseEntity<ResponseBean>> response = this.asyncRestTemplate.exchange("http://productservice/product/all", HttpMethod.GET,request,ResponseBean.class);

		response.addCallback(new ListenableFutureCallback<ResponseEntity<ResponseBean>>() {
			@Override
			public void onSuccess(ResponseEntity<ResponseBean> response) {
				deferredResult.setResult(response);
			}

			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
				ResponseBean response = new ResponseBean(false, throwable.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value());
				deferredResult.setErrorResult(new ResponseEntity<ResponseBean>(response, HttpStatus.OK));
			}
		});
		
		return deferredResult;
	}
	
	
	public DeferredResult<ResponseEntity<ResponseBean>> findAllFallBack2(){
		System.out.println("----findAllFallBack2----");
		DeferredResult<ResponseEntity<ResponseBean>> deferredResult = new DeferredResult<>();
		ResponseBean response = new ResponseBean(false, "Enable to connect to requested Product Service, please try after some time", "error", null);
		deferredResult.setErrorResult(new ResponseEntity<>(response, HttpStatus.OK));
		return deferredResult;
	}
	
}
