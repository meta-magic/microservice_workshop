package com.metamagic.ms.controller.read;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.OrderReadService;

/**
 * @author sagar 
 * THIS CONTROLLER HANDLE REQUEST FROM UI 
 * 1.HANDLE REQUEST AND SEND TO SERVICE 
 * 2.FALLBACK MECHANISM
 * 
 */
@RestController
@RequestMapping("/order/query")
public class OrderReadController {

	@Autowired
	private OrderReadService orderReadService;

	@RequestMapping(value = "/orderhistory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> findAll(HttpServletRequest request) {
		return orderReadService.findAll(request);
	}
}
