package com.metamagic.ms.service.write;

import com.metamagic.ms.dto.UserDTO;
import com.metamagic.ms.exception.CustomException;

/**
 * @author sagar
 * 
 * THIS INTERFACE IS USED FOR USER WRITE OPERATIONS LIKE CREATEUSER
 */
public interface UserWriteService {
	
	public void createUser(UserDTO userDTO) throws CustomException;
}