package com.metamagic.ms.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.aspect.LoginInfoHelperBean;
import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.dto.CartDTO;
import com.metamagic.ms.dto.CartStatus;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.service.read.ShoppingCartReadService;

/**
 * @author sagar
 * 
 *         THIS CONTROLLER IS USED FOR READING SHOPPING CART
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
	 */
	@RequestMapping(value = "/fecthcart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> fetchcart(@RequestBody CartStatus cartStatus) {
		try {
			ResponseBean response = new ResponseBean(true, "User Cart retrivered Successfully", "success",
					shoppingCartService.fetchcart(loginInfoHelperBean.getUserId(), cartStatus.getStatus()));
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		} catch (RepositoryException e) {
			ResponseBean response = new ResponseBean(false, "User cart retrived failed", "failed", null);
			return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
		}
	}
}
