package com.metamagic.ms.controller.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.aop.LoginInfoHelperBean;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.dto.CartDTO;
import com.metamagic.ms.service.write.ShoppingCartWriteService;

/**
 * @author sagar
 * THIS CONTROLLER IS USED FOR WRTIE CART OPERATION
 */
@RestController
@RequestMapping("/shoppingcart/write")
@Scope("request")
public class ShoppingCartWriteController {

	@Autowired
	private LoginInfoHelperBean loginInfoHelperBean;
	
	@Autowired
	private ShoppingCartWriteService shoppingCartService;
	
	/**
	 * THIS METHOD IS USED FOR CREATE CART
	 * */
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<ResponseBean> createCart(@RequestBody CartDTO cart){
		cart.setCartId(loginInfoHelperBean.getUserId());
		cart.setCustomerId(loginInfoHelperBean.getUserId());
		shoppingCartService.createCart(cart);
		ResponseBean response = new ResponseBean(true, "cart created.", "success", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
	@PostMapping("/additem")
	public @ResponseBody ResponseEntity<ResponseBean> addItem(@RequestBody CartDTO cart){
		cart.setCartId(loginInfoHelperBean.getUserId());
		cart.setCustomerId(loginInfoHelperBean.getUserId());
		shoppingCartService.addItem(cart);
		ResponseBean response = new ResponseBean(true, "Item added.", "success", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
	@PostMapping("/removeitem")
	public @ResponseBody ResponseEntity<ResponseBean> removeItem(@RequestBody CartDTO cart){
		cart.setCartId(loginInfoHelperBean.getUserId());
		cart.setCustomerId(loginInfoHelperBean.getUserId());
		shoppingCartService.removeItem(cart);
		ResponseBean response = new ResponseBean(true, "Item removed.", "success", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
	@PostMapping("/placeorder")
	public @ResponseBody ResponseEntity<ResponseBean> placeOrder(@RequestBody CartDTO cart){
		cart.setCartId(loginInfoHelperBean.getUserId());
		cart.setCustomerId(loginInfoHelperBean.getUserId());
		shoppingCartService.placeOrder(cart);
		ResponseBean response = new ResponseBean(true, "order placed.", "success", null);
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
	
}
