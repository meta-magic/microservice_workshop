package com.metamagic.ms.controller.read;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.read.ProductReadService;

@RestController
@RequestMapping("/product/query")
public class ProductQueryController {


	@Autowired
	private ProductReadService productService;
	
	@RequestMapping(value="/findall", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<ResponseBean> findAll(){
		ResponseBean response = new ResponseBean(true,"Data retrieved successfully","success",productService.getProducts());
		
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<ResponseBean>> all() {
		List<String> data = new ArrayList<String>();
		data.add("Item 1");
		ResponseBean response = new ResponseBean(true,"Data retrieved successfully","success",data);
		
		DeferredResult<ResponseEntity<ResponseBean>> deferredResult = new DeferredResult<ResponseEntity<ResponseBean>>();
		deferredResult.setResult(new ResponseEntity<ResponseBean>(response, HttpStatus.OK));
		
		
		return deferredResult;
	
	}
	
}
