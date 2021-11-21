package org.balumba.sagademo.saga.adapters;

import org.balumba.sagademo.business.order.OrderService;
import org.balumba.sagademo.saga.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApproveOrderAdapter implements JavaDelegate {

    final static Logger logger = LoggerFactory.getLogger(ApproveOrderAdapter.class);

    private final OrderService orderService;

    public ApproveOrderAdapter(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(DelegateExecution context) {
        var variables = new ProcessVariables(context);
        var orderId = variables.getOrderId().orElseThrow();
        orderService.approveOrder(orderId);
        logger.info("Order {} approved", orderId);
    }
}
