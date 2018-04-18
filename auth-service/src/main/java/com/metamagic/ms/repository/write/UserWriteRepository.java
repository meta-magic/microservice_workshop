package com.metamagic.ms.repository.write;

import com.metamagic.ms.bean.User;

/**
 * @author sagar
 *	
 * THIS INTERFACE USED FOR UER OPERATION
 */
public interface UserWriteRepository {

	public User save(User user);
}
