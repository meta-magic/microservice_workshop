package com.metamagic.ms.repository.read;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.repository.GenericRepository;

/**
 * @author sagar
 * 
 * THIS REPOSITORY USED FOR READ ORDER OPERATION
 */
@Repository
public class OrderReadRepositoryImpl extends GenericRepository<OrderDocument> implements OrderReadRepository {

	@Override
	public List<OrderDocument> findAll(String userId) {
		PersistenceManager pm = pm();
		Query query = pm.newQuery(OrderDocument.class);
		query.setFilter("this.userId == :userId");
		List<OrderDocument> orDocuments = (List<OrderDocument>) query.execute(userId);
		pm.close();
		return orDocuments;
	}

}
