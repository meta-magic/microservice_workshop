package com.metamagic.ms.repository.read;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.User;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar
 * 
 * THIS REPOSITORY IMPLMENTATION IS FOR USER READ OPERAIONS
 */
@Repository
public class UserReadRepositoryImpl extends GenericRepository<User> implements UserReadRepository {

	@Override
	public User findByUserId(String userId) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(User.class);
			query.setFilter("userId == :userId");
			query.setUnique(true);
			User user = (User) query.execute(userId);
			return user;
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}
}
