package com.metamagic.ms.repository.read;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.entity.OrderDocument.Status;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar
 * 
 *         THIS REPOSITORY USED FOR READ ORDER OPERATION
 */
@Repository
public class OrderReadRepositoryImpl extends GenericRepository<OrderDocument> implements OrderReadRepository {

	@Override
	public List<OrderDocument> findAll(String userId) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(OrderDocument.class);
			query.setFilter("this.userId == :userId");
			List<OrderDocument> orDocuments = (List<OrderDocument>) query.execute(userId);
			return orDocuments;
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}

	}

	@Override
	public OrderDocument findByUserIdAndStatus(String userId, Status status) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(OrderDocument.class);
			query.setFilter("this.userId == :userId && this.status== :status");
			query.setUnique(true);
			OrderDocument document = (OrderDocument) query.execute(userId, status);
			return pm.detachCopy(document);
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}

	@Override
	public OrderDocument findByOrderId(String orderId) throws RepositoryException {
		PersistenceManager pm = pm();
		try {
			Query query = pm.newQuery(OrderDocument.class);
			query.setFilter("this.orderId == :orderId");
			query.setUnique(true);
			OrderDocument orderDocument = (OrderDocument) query.execute(orderId);
			return pm.detachCopy(orderDocument);
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			pm.close();
		}
	}

}
