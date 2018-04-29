package com.metamagic.ms.service.read;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.metamagic.ms.bean.ResponseBean;

/**
 * @author sagar 
 * THIS SERVICE INTERFACE IS USED FOR ORDER OPERATION LIKE ORDER
 *         HISTORY OF ORDER
 */
public interface OrderReadService {

	public ResponseEntity<ResponseBean> findAll(HttpServletRequest request);

	public ResponseEntity<ResponseBean> getOrderDetails(HttpServletRequest request);

}
