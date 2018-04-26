package com.metamagic.ms.controller.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.service.write.OrderWriteService;

/**
 * @author sagar 
 * 
 * THIS CONTROLLER HANDLE REQUEST FROM UI 1.HANDLE REQUEST AND
 *         SEND TO SERVICE 2.FALLBACK MECHANISM
 * 
 */
@RestController
@RequestMapping("/order/write")
public class OrderWriteController {

	@Autowired
	private OrderWriteService orderWriteService;

	@RequestMapping(value = "/addshippingaddress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> addShippingAddress(@RequestBody Object object) {
		return orderWriteService.addShippingAddress(object);
	}

	@RequestMapping(value = "/addpaymentdetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> addpaymentdetails(@RequestBody Object object) {
		return orderWriteService.addPaymentDetails(object);
	}
}
