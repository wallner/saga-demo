package org.balumba.sagademo.saga.adapters;

import org.balumba.sagademo.business.order.OrderCanceledException;
import org.balumba.sagademo.business.order.OrderService;
import org.balumba.sagademo.saga.ProcessVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CancelOrderAdapter implements JavaDelegate {

    final static Logger logger = LoggerFactory.getLogger(CancelOrderAdapter.class);

    final private OrderService orderService;

    public CancelOrderAdapter(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(DelegateExecution execution) {
        var processVariables = new ProcessVariables(execution);
        var orderId = processVariables.getOrderId().orElse(0L);

        orderService.refuseOrder(orderId);

        logger.info("Order {} Canceled.", orderId);

        throw new OrderCanceledException();

    }
}
