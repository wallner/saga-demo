package org.balumba.sagademo.saga;

import org.balumba.sagademo.business.order.Order;
import org.balumba.sagademo.business.order.OrderDto;
import org.camunda.bpm.engine.delegate.VariableScope;

import java.util.Optional;


public class ProcessVariables {
    public static final String PROCESS_KEY_order = "orderSaga";
    public static final String VAR_received_order = "receivedOrder";
    public static final String VAR_order_id = "orderId";

    private final VariableScope variableScope;

    public ProcessVariables(VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public Optional<OrderDto> getReceivedOrder() {
        return Optional.ofNullable((OrderDto) variableScope.getVariable(VAR_received_order));
    }

    public void setOrderId(Long orderId) {
        variableScope.setVariable(VAR_order_id, orderId);
    }

    public Optional<Long> getOrderId() {
        return Optional.ofNullable((Long) variableScope.getVariable(VAR_order_id));
    }
}
