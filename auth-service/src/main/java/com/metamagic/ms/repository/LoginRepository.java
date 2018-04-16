package com.metamagic.ms.repository;

import com.metamagic.ms.bean.Login;

public interface LoginRepository {

	public Login findByLoginId(String loginId);
}
