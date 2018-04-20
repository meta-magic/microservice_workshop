package com.metamagic.ms.service.read;

import com.metamagic.ms.bean.LoginResponse;
import com.metamagic.ms.dto.LoginDTO;
import com.metamagic.ms.exception.BussinessException;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * THIS SERVICE INTERFACE IS USED FOR AUTHENTICATE
 */
public interface AuthService {

	public LoginResponse authenticate(LoginDTO loginDTO) throws RepositoryException, BussinessException;
}
