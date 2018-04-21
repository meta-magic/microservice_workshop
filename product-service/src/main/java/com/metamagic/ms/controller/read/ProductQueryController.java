package com.metamagic.ms.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.service.read.ProductReadService;

/**
 * @author sagar
 * 
 *         THIS CONTROLLER USED FOR PRODUCT READ
 */
@RestController
@RequestMapping("/product/query")
public class ProductQueryController {

	@Autowired
	private ProductReadService productService;

	/**
	 * ThIS METHOD IS USED FOR FINDING ALL PRODUCT
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> findAll() {
		try {
			ResponseBean response = new ResponseBean(true, "Data retrieved successfully", "success",
					productService.getProducts());
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		} catch (RepositoryException e) {
			ResponseBean response = new ResponseBean(false, e.getMessage(), "failed", null);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		}
	}
}
