package com.metamagic.ms.controller.write;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.write.ShoppingCartWriteService;

/**
 * @author sagar THIS CONTROLLER USED FOR WRITE SHOPPING CART OPERATION
 */
@RestController
@RequestMapping("/shoppingcart/write")
public class ShoppingCartWriteController {

	@Autowired
	private ShoppingCartWriteService cartWriteService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> create(@RequestBody Object payload, HttpServletRequest request) {
		return cartWriteService.create(payload, request);
	}

	@RequestMapping(value = "/additem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> addItem(@RequestBody Object payload, HttpServletRequest request) {
		return cartWriteService.addItem(payload, request);
	}

	@RequestMapping(value = "/removeitem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> removeItem(@RequestBody Object payload, HttpServletRequest request) {
		return cartWriteService.removeItem(payload, request);
	}

	@RequestMapping(value = "/placeorder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> placeorder(@RequestBody Object payload, HttpServletRequest request) {
		return cartWriteService.placeorder(payload, request);
	}

}
