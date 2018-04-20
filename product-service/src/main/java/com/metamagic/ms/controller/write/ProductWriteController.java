package com.metamagic.ms.controller.write;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.entity.Product;
import com.metamagic.ms.service.write.ProductWriteService;

import ch.qos.logback.classic.Logger;

/**
 * @author sagar
 * THIS CONTROLLER USED FOR WRITE PRODUCT OPERATION
 */
@RestController
@RequestMapping("/product/write")
public class ProductWriteController {
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductWriteController.class);

	@Autowired
	private ProductWriteService productService;

	/**
	 * THIS METHOD IS USED FOR SAVE PRODUCT 
	 * */
	@RequestMapping(value="/save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<ResponseBean> save(@RequestBody Product payload){
		LOGGER.info("Product save started");
		productService.save(payload);
		ResponseBean response = new ResponseBean(true,"Data retrieved successfully","success",null);
		LOGGER.info("Product save succesfully.");
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
	
}
