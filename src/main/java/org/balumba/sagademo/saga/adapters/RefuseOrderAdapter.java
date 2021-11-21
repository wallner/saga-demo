package org.balumba.sagademo.saga.adapters;

import org.balumba.sagademo.business.order.OrderService;
import org.balumba.sagademo.saga.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RefuseOrderAdapter implements JavaDelegate {

    private final static Logger logger = LoggerFactory.getLogger(RefuseOrderAdapter.class);

    private final OrderService orderService;

    public RefuseOrderAdapter(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(DelegateExecution context) {

        var variables = new ProcessVariables(context);
        var orderId = variables.getOrderId().orElseThrow();

        orderService.refuseOrder(orderId);
        
        logger.info("Order {} refused", orderId);


    }
}
