package com.metamagic.ms.service.read;

import com.metamagic.ms.bean.LoginResponse;
import com.metamagic.ms.dto.LoginDTO;

public interface AuthService {

	public LoginResponse authenticate(LoginDTO loginDTO);
}
