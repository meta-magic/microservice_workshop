package com.metamagic.ms.service;

import org.springframework.http.ResponseEntity;

import com.metamagic.ms.bean.ResponseBean;

public interface AuthService {

	public ResponseEntity<ResponseBean> authenticate(Object object);

}
