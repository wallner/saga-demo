package org.balumba.sagademo.saga;

import org.balumba.sagademo.business.order.OrderDto;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Service;

import static org.balumba.sagademo.saga.ProcessVariables.*;
import static org.camunda.bpm.engine.variable.Variables.SerializationDataFormats.JSON;

@Service
public class OrderSaga {

    private final ProcessEngine camunda;

    public OrderSaga(ProcessEngine camunda) {
        this.camunda = camunda;
    }

    public Long placeOrder(OrderDto order) {
        ObjectValue orderValue = Variables
                .objectValue(order)
                .serializationDataFormat(JSON)
                .create();

        ProcessInstanceWithVariables instanceVariables = camunda.getRuntimeService()
                .createProcessInstanceByKey(PROCESS_KEY_order)
                .setVariables(Variables.putValue(VAR_received_order, orderValue))
                .executeWithVariablesInReturn();

        return instanceVariables.getVariables().getValue(VAR_order_id, Long.class);
    }
}
