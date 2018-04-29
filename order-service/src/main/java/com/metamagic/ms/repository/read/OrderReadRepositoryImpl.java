package com.metamagic.ms.repository.read;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.Order;
import com.metamagic.ms.entity.Order.Status;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar
 * 
 *         THIS REPOSITORY USED FOR READ ORDER OPERATION
 */
@Repository
public class OrderReadRepositoryImpl extends GenericRepository<Order> implements OrderReadRepository {

	@Override
	public List<Order> findAll(String userId) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(Order.class);
			query.setFilter("this.userId == :userId");
			List<Order> orDocuments = (List<Order>) query.execute(userId);
			return orDocuments;
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}

	}

	@Override
	public Order findByUserIdAndStatus(String userId, Status status) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(Order.class);
			query.setFilter("this.userId == :userId && this.status== :status");
			query.setUnique(true);
			Order document = (Order) query.execute(userId, status);
			return pm.detachCopy(document);
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}

	@Override
	public Order findByOrderId(String orderId) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(Order.class);
			query.setFilter("this.orderId == :orderId");
			query.setUnique(true);
			Order orderDocument = (Order) query.execute(orderId);
			return pm.detachCopy(orderDocument);
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}

}
