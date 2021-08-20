package org.balumba.sagademo.saga.adapters;

import org.balumba.sagademo.business.customer.CustomerService;
import org.balumba.sagademo.saga.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReserveCreditAdapter implements JavaDelegate {

    final static Logger logger = LoggerFactory.getLogger(ReserveCreditAdapter.class);

    private final CustomerService customerService;

    public ReserveCreditAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execute(DelegateExecution context) throws Exception {
        var variables = new ProcessVariables(context);
        var orderId = variables.getOrderId().orElseThrow();

        logger.info("Reserving Credit for Order {}", orderId);

        customerService.reserveCredit(orderId);
    }
}
