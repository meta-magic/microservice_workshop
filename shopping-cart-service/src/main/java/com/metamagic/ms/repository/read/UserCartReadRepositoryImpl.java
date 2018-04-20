package com.metamagic.ms.repository.read;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar THIS REPOSITORY CLASS IS USED FOR USER CART READ
 */
@Repository
public class UserCartReadRepositoryImpl extends GenericRepository<UserCart> implements UserCartReadRepository {

	@Override
	public UserCart findByUserIdAndActive(String userId, String completed) {
		PersistenceManager pm = pm();
		UserCart userCart = null;
		try {
			Query<UserCart> query = pm.newQuery(UserCart.class);
			query.setFilter("userId == :userId && status == :completed");
			query.setUnique(true);
			userCart = (UserCart) query.execute(userId, completed);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
		return userCart;
	}

}
