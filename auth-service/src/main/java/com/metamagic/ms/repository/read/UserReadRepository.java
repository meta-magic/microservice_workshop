package com.metamagic.ms.repository.read;

import com.metamagic.ms.bean.User;

public interface UserReadRepository {

	public User findByUserId(String loginId);
}
