package com.metamagic.ms.controller.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.read.OrderReadService;

/**
 * @author sagar
 * 
 * THIS CONTROLLER IS USED FOR READ ORDER
 *
 */
@RestController
@RequestMapping("/order/query")
@Scope("request")
public class OrderQueryController {

	@Autowired
	private OrderReadService orderReadService;
	
	/**
	 * THIS METHOD IS USED FOR GET ORDER HISTORY 
	 * */
	@RequestMapping(value="/orderhistory", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<ResponseBean> findAll(){
		ResponseBean response = new ResponseBean(true,"Data retrieved successfully","success",orderReadService.findAll());
		
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
	
}
