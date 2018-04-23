package com.metamagic.ms.service;

import org.springframework.http.ResponseEntity;

import com.metamagic.ms.bean.ResponseBean;

/**
 * @author sagar 
 * 
 * THIS SERVICE INTEFACE IS USED FOR HANDLING FALLBACK MECHANISM
 */
public interface UserService {

	public ResponseEntity<ResponseBean> create(Object object);

}
