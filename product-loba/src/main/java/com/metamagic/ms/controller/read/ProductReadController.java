package com.metamagic.ms.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.read.ProductReadService;

/**
 * @author sagar
 * 
 * THIS CONTROLLER IS USED FOR PRODUCT READ OPERATION
 */
@RestController
@RequestMapping("/product/query")
public class ProductReadController {
	
	@Autowired
	private ProductReadService productReadService;
	
	@RequestMapping(value = "/findall", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> findAll(){
		return productReadService.findAll();
	}
}
