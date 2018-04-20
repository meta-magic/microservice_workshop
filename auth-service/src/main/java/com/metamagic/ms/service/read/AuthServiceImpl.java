package com.metamagic.ms.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.LoginResponse;
import com.metamagic.ms.dto.LoginDTO;
import com.metamagic.ms.entity.User;
import com.metamagic.ms.exception.BussinessException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.UserReadRepository;
import com.metamagic.ms.service.TokenService;

/**
 * @author sagar THIS SERVICE I USED FOR AUTHENTICATE USER
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserReadRepository loginRepository;

	@Autowired
	private TokenService tokenService;

	/**
	 * THIS METHOD IS USED FOR AUTHENTICATE AND ADD TOKEN
	 * 
	 * @throws RepositoryException
	 */
	@Override
	public LoginResponse authenticate(LoginDTO loginDTO) throws RepositoryException, BussinessException {
//		if (loginDTO.getUserId() != null && loginDTO.getPassword() != null) {
			User login = loginRepository.findByUserId(loginDTO.getUserId());
			if (login != null && loginDTO.getPassword().equals(login.getPassword())) {
				LoginResponse loginResponse = new LoginResponse(tokenService.generateToken(login.getId()));
				return loginResponse;
			}
//		}
		throw new BussinessException("User not found.");
	}

}
