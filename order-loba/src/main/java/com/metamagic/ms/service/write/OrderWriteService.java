package com.metamagic.ms.service.write;

import org.springframework.http.ResponseEntity;

import com.metamagic.ms.bean.ResponseBean;

/**
 * @author sagar
 * 
 *         THIS SERVICE IS USED FOR HANDLING ORDER ADDRESS UPDATE AND PAYMENT
 *         REQEUST FROM UI
 */

public interface OrderWriteService {

	public ResponseEntity<ResponseBean> addShippingAddress(Object object);

	public ResponseEntity<ResponseBean> addPaymentDetails(Object object);

}
