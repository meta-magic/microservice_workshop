package com.metamagic.ms.service.write;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.metamagic.ms.bean.ResponseBean;

/**
 * @author sagar
 * 
 * THIS SERVICE INTERFACE IS USED FOR PRODUCT WRTIE OPERATION
 */
public interface ProductWriteService {

	public ResponseEntity<ResponseBean> save(Object payload, HttpServletRequest request);

	public DeferredResult<ResponseEntity<ResponseBean>> findAll();

}
