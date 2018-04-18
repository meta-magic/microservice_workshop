package com.metamagic.ms.service.read;

import com.metamagic.ms.bean.User;

/**
 * @author sagar
 * 
 * THIS INTERFACE USED FOR USER COMMON OPERATION 
 */
public interface UserReadService {
	public User findByLoginId(String loginId);
}
