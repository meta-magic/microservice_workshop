package com.metamagic.ms.service.read;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.metamagic.ms.bean.ResponseBean;

/**
 * @author sagar 
 * THIS SERVICE INTERFACE IS USED FOR SHOPPING READ OPERATION
 */
public interface ShoppingCartReadService {

	public ResponseEntity<ResponseBean> fecthcart(Object payload, HttpServletRequest request);

}
