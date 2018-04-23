package com.metamagic.ms.repository.read;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.UserCart;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar THIS REPOSITORY CLASS IS USED FOR USER CART READ
 */
@Repository
public class UserCartReadRepositoryImpl extends GenericRepository<UserCart> implements UserCartReadRepository {

	@Override
	public UserCart findByUserIdAndActive(String userId, String completed) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query<UserCart> query = pm.newQuery(UserCart.class);
			query.setFilter("userId == :userId && status == :completed");
			query.setUnique(true);
			UserCart userCart = (UserCart) pm.detachCopy(query.execute(userId, completed));
			return userCart;
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}

	}

}
