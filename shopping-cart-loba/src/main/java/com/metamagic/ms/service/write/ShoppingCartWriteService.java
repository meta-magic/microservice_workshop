package com.metamagic.ms.service.write;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.metamagic.ms.bean.ResponseBean;

/**
 * @author sagar
 * 
 * THIS SERRVICE INTERFACE IS USED FOR SHOPPING CART WRTIE OPERATION
 */
public interface ShoppingCartWriteService {

	public ResponseEntity<ResponseBean> create(Object payload, HttpServletRequest request);

	public ResponseEntity<ResponseBean> addItem(Object payload, HttpServletRequest request);

	public ResponseEntity<ResponseBean> removeItem(Object payload, HttpServletRequest request);

	public ResponseEntity<ResponseBean> placeorder(Object payload, HttpServletRequest request);

}
