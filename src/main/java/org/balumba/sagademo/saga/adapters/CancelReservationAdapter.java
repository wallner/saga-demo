package org.balumba.sagademo.saga.adapters;

import org.balumba.sagademo.business.customer.CustomerService;
import org.balumba.sagademo.business.order.OrderService;
import org.balumba.sagademo.saga.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CancelReservationAdapter implements JavaDelegate {

    final static Logger logger = LoggerFactory.getLogger(CancelReservationAdapter.class);

    private final CustomerService customerService;
    private final OrderService orderService;

    public CancelReservationAdapter(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Override
    public void execute(DelegateExecution context) throws Exception {
        var variables = new ProcessVariables(context);

        var orderId = variables.getOrderId().orElseThrow();
        customerService.cancelReservation(orderId);
        logger.info("Reservation for order {} canceled", orderId);
    }
}
