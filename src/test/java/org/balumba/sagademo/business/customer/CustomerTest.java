package org.balumba.sagademo.business.customer;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CustomerTest {

    private Customer customer = new Customer("Homer", "5 EUR");

    @Test
    void amount_gets_subtracted_from_credit() {
        customer.decreaseCreditLimit(FastMoney.parse("3 EUR"));

        assertThat(customer.getCreditLimit()).isEqualTo(FastMoney.parse("2 EUR"));
    }

    @Test
    void credit_cannot_get_negative() {
        assertThatExceptionOfType(BadCreditOperationException.class)
                .isThrownBy(() -> customer.decreaseCreditLimit(FastMoney.parse("6 EUR")))
                .withMessage("Insufficient Credit");
    }

}