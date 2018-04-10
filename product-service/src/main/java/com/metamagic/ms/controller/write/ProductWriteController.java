package com.metamagic.ms.controller.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.Product;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.write.ProductWriteService;

@RestController
@RequestMapping("/product/write")
public class ProductWriteController {
	
	@Autowired
	private ProductWriteService productService;


	@RequestMapping(value="/save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<ResponseBean> save(@RequestBody Product payload){
		System.out.println("payload--"+payload.getName());
		productService.save(payload);
		ResponseBean response = new ResponseBean(true,"Data retrieved successfully","success",null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
	
}
