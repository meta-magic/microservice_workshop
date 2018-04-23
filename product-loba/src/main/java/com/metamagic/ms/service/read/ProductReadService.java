package com.metamagic.ms.service.read;

import org.springframework.http.ResponseEntity;

import com.metamagic.ms.bean.ResponseBean;

/**
 * @author sagar 
 * THIS SERVICE INTERFACE IS USED FOR PRODUCT READ OPERATION
 */
public interface ProductReadService {

	public ResponseEntity<ResponseBean> findAll();

}
