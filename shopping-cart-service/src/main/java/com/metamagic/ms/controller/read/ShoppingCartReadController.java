package com.metamagic.ms.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.Cart;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.read.ShoppingCartReadService;

@RestController
@RequestMapping("/shoppingcart/read")
public class ShoppingCartReadController {
	
	@Autowired
	private ShoppingCartReadService shoppingCartService;

	@RequestMapping(value ="/fecthcart", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> fetchcart(@RequestBody Cart cart){
		
		ResponseBean response = new ResponseBean(true, "User Cart retrivered Successfully", "success", shoppingCartService.fetchcart(cart.getCustomerId()));
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

}
