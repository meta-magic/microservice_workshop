package com.metamagic.ms.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.aop.LoginInfoHelperBean;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.read.ShoppingCartReadService;

/**
 * @author sagar
 * 
 * THIS CONTROLLER IS USED FOR READING SHOPPING CART
 */
@RestController
@RequestMapping("/shoppingcart/read")
@Scope("request")
public class ShoppingCartReadController {
	
	@Autowired
	private ShoppingCartReadService shoppingCartService;
	
	@Autowired
	private LoginInfoHelperBean loginInfoHelperBean;

	/**
	 * THIS METHOD IS USED FOR FETCH CART 
	 * */
	@RequestMapping(value ="/fecthcart", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> fetchcart(){
		
		ResponseBean response = new ResponseBean(true, "User Cart retrivered Successfully", "success", shoppingCartService.fetchcart(loginInfoHelperBean.getUserId()));
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

}
