package com.metamagic.ms.controller.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metamagic.ms.bean.ResponseBean;
import com.metamagic.ms.dto.PaymentDTO;
import com.metamagic.ms.dto.ShippingAddressDTO;
import com.metamagic.ms.exception.BussinessException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.service.write.OrderWriteService;

/**
 * @author sagar
 * 
 *         THIS CONTROLLER IS USED FOR ADDING SHIPPING ADDDRESS AND PAYMENT
 *         DETAILS OD ORDER
 */

@RestController
@RequestMapping("/order/write")
@Scope("request")
public class OrderWriteController {

	@Autowired
	private OrderWriteService orderWriteService;

	/**
	 * THIS METHOD IS USED FOR ADD SHIPPING ADDRESS
	 */
	@PostMapping(value = "/addshippingaddress", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> addShippingAddress(@RequestBody ShippingAddressDTO addressDTO) {
		try {
			orderWriteService.addShippingAddressDetails(addressDTO);
			ResponseBean responseBean = new ResponseBean(true, "Shipping address added successfully.", "success",
					null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (RepositoryException e) {
			ResponseBean responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (BussinessException e) {
			ResponseBean responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		}
	}

	/**
	 * THIS METHOD IS USED FOR ADD PAYMENT DETAILS
	 */
	@PostMapping(value = "/addpaymentdetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseBean> addPaymentDetails(@RequestBody PaymentDTO paymentDTO) {
		try {
			orderWriteService.addPaymentDetails(paymentDTO);
			ResponseBean responseBean = new ResponseBean(true, "Payment done successfully.", "success",
					null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (RepositoryException e) {
			ResponseBean responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (BussinessException e) {
			ResponseBean responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean responseBean = new ResponseBean(false, e.getMessage(), "failure", null);
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		}
	}

}
