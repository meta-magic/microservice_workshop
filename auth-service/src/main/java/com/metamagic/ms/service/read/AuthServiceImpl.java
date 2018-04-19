package com.metamagic.ms.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metamagic.ms.bean.LoginResponse;
import com.metamagic.ms.dto.LoginDTO;
import com.metamagic.ms.entity.User;
import com.metamagic.ms.repository.read.UserReadRepository;
import com.metamagic.ms.service.TokenService;

/**
 * @author sagar
 * THIS SERVICE I USED FOR AUTHENTICATE USER
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserReadRepository loginRepository;

	@Autowired
	private TokenService tokenService;

	/**
	 * THIS METHOD IS USED FOR AUTHENTICATE AND ADD TOKEN
	 * */
	@Override
	public LoginResponse authenticate(LoginDTO loginDTO) {
		LoginResponse loginResponse = null;
		if (loginDTO.getUserId() != null && loginDTO.getPassword() != null) {
			User login = loginRepository.findByUserId(loginDTO.getUserId());
			if (login != null) {
				if (loginDTO.getUserId().equals(login.getUserId())
						&& loginDTO.getPassword().equals(login.getPassword())) {
					loginResponse = new LoginResponse(tokenService.generateToken(login.getId()));
				}
			}
		}
		return loginResponse;
	}

}
