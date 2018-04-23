package com.metamagic.ms.controller.write;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.write.ProductWriteService;

/**
 * @author sagar
 * 
 * THIS REST CONTROLLER USE FOR PRODUCT WRITE OPERATION
 */
@RestController
@RequestMapping("/product/write")
public class ProductWriteController {

	@Autowired
	private ProductWriteService productWriteService;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> save(@RequestBody Object payload, HttpServletRequest request) {
		return productWriteService.save(payload, request);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<ResponseEntity<ResponseBean>> finAll() {
		return productWriteService.findAll();
	}

}
