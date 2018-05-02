package com.metamagic.ms.service.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.metamagic.ms.dto.PaymentDTO;
import com.metamagic.ms.dto.ShippingAddressDTO;
import com.metamagic.ms.entity.Order;
import com.metamagic.ms.entity.Order.Status;
import com.metamagic.ms.events.integration.PaymentInitiatedEvent;
import com.metamagic.ms.exception.InvalidDataException;
import com.metamagic.ms.exception.RepositoryException;
import com.metamagic.ms.repository.read.OrderReadRepository;
import com.metamagic.ms.repository.write.OrderWriteRepository;
import com.metamagic.ms.specification.OrderAmountSpecification;
import com.metamagic.ms.specification.OrderStatusSpecification;
import com.metamagic.ms.specification.core.Specification;

/**
 * @author sagar THIS ORDER SERVICE USED FOR WRITE THE ORDER
 */
@Service
public class OrderWriteServiceImpl implements OrderWriteService {

	@Autowired
	private OrderWriteRepository orderWriteRepository;

	@Autowired
	private OrderReadRepository orderReadRepository;

	@Autowired
	private KafkaTemplate<String, PaymentInitiatedEvent> kafkaTemplate;

	/**
	 * THIS METHOD IS USED FOR SAVE THE ORDER
	 */
	public Order save(Order order) throws RepositoryException, Exception {

		Order order2 = orderWriteRepository.save(order);

		this.applyDiscount(order2.getOrderId());
		return order2;
	}

	/**
	 * adds shipping address to order
	 * 
	 * @param dto
	 * @throws InvalidDataException
	 * @throws Exception
	 */
	public void addShippingAddressDetails(ShippingAddressDTO dto) throws Exception {
		Order order = orderReadRepository.findByOrderId(dto.getOrderId());
		order.addShippingAddress(dto.getLabel(), dto.getAddress(), dto.getCountry(), dto.getProvince(),
				dto.getPostalcode(), dto.getCity());
		orderWriteRepository.save(order);
	}

	/**
	 * adds payment details to order
	 * 
	 * @param dto
	 * @throws InvalidDataException
	 * @throws Exception
	 */
	public void addPaymentDetails(PaymentDTO dto) throws InvalidDataException, Exception {
		Order order = orderReadRepository.findByOrderId(dto.getOrderId());
		order.addPaymentDetails(dto.getMode());
		orderWriteRepository.save(order);
		kafkaTemplate.send("order_payment", new PaymentInitiatedEvent(order.getUserId(), order.getOrderId(),
				order.getPayment().getPaymentid(), order.getTotal(), dto.getMode()));
	}

	/**
	 * APPLY THE DISCOUNT BASED ON SPECIFICATION SPECIFIED
	 */
	private void applyDiscount(String orderId) throws RepositoryException, Exception {

		Order order = orderReadRepository.findByOrderId(orderId);
		Specification spe = this.discountSpecification();

		if (spe.isValid(order)) {
			try {
				order.applyDiscount();
				orderWriteRepository.save(order);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Return discount specification
	 * 
	 * @return {@link Specification}
	 */
	private Specification discountSpecification() {
		return new OrderStatusSpecification(Status.PREPARING).or(new OrderStatusSpecification(Status.PAYMENT_EXPECTED))
				.and(new OrderAmountSpecification(5000.00));
	}
}
