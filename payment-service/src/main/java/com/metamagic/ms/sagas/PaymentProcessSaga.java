package com.metamagic.ms.sagas;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.metamagic.ms.commands.CompletePaymentCommand;
import com.metamagic.ms.commands.DeclinePaymentCommand;
import com.metamagic.ms.events.PaymentProcessStartedEvent;

import static org.axonframework.eventhandling.saga.SagaLifecycle.end;;

@Saga
public class PaymentProcessSaga {

	private final double maxLimit = 50000;
	
	@Autowired
	private CommandGateway commandGateway;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "statusId")
	public void handle(PaymentProcessStartedEvent paymentProcessStartedEvent)  {
		
		if(paymentProcessStartedEvent.getAmount() > maxLimit) {
			commandGateway.send(new DeclinePaymentCommand(paymentProcessStartedEvent.getStatusId(), "DECLINED", "Order amount more than max limit."));
		} else {
			commandGateway.send(new CompletePaymentCommand(paymentProcessStartedEvent.getStatusId(), "COMPLETED", "Payment completed successfully."));
		}
		end();
	}
}
