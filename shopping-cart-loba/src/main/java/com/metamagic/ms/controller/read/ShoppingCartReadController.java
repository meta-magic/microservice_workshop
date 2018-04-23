package com.metamagic.ms.controller.read;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.read.ShoppingCartReadService;

/**
 * @author sagar 
 * THIS CONTROLLER IS USED FOR READING SHOPPING CART OPERTAIONS
 */
@RestController
@RequestMapping("/shoppingcart/read")
public class ShoppingCartReadController {

	@Autowired
	private ShoppingCartReadService cartReadService;

	@RequestMapping(value = "/fecthcart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> fecthcart(@RequestBody Object payload, HttpServletRequest request) {
		return cartReadService.fecthcart(payload, request);
	}
}
