package org.balumba.sagademo.business;

import org.balumba.sagademo.business.customer.Customer;
import org.balumba.sagademo.business.order.Order;
import org.balumba.sagademo.business.order.OrderState;
import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    private final String productDescription = "Duff Beer";

    private final Customer customer = new Customer("Homer", "100 EUR");

    @Test
    void order_gets_constructed_correctly() {
        var order = new Order("Homer", productDescription, "10 EUR");

        assertThat(order.getState()).isEqualTo(OrderState.PENDING);
        assertThat(order.getCustomer()).isEqualTo(customer.getName());
        assertThat(order.getPrice()).isEqualTo(FastMoney.of(10, "EUR"));
    }

    @Test
    void order_can_be_approved() {
        var order = new Order("Homer", productDescription, "10 EUR");

        order.approve();

        assertThat(order.getState()).isEqualTo(OrderState.APPROVED);
    }

    @Test
    void order_can_be_rejected() {
        var order = new Order("Homer", productDescription, "10 EUR");

        order.cancel();

        assertThat(order.getState()).isEqualTo(OrderState.CANCELED);
    }


}
