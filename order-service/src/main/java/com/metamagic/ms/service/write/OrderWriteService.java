package com.metamagic.ms.service.write;

import com.metamagic.ms.dto.PaymentDTO;
import com.metamagic.ms.dto.ShippingAddressDTO;
import com.metamagic.ms.entity.OrderDocument;
import com.metamagic.ms.exception.InvalidDataException;
import com.metamagic.ms.exception.RepositoryException;

/**
 * @author sagar
 * 
 *         THIS ITERFACE IS USED FOR ORDER WRITE OPERATION
 */
public interface OrderWriteService {
	public OrderDocument save(OrderDocument order) throws RepositoryException;

	public void addShippingAddressDetails(ShippingAddressDTO dto) throws Exception;

	public void addPaymentDetails(PaymentDTO dto) throws InvalidDataException, Exception;
}
