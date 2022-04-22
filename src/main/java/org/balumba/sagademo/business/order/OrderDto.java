package org.balumba.sagademo.business.order;

import org.javamoney.moneta.FastMoney;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record OrderDto(@NotBlank String customer, @NotEmpty String description, @NotNull MonetaryAmount price) {

    public OrderDto(@NotBlank String customer, @NotEmpty String description, @NotEmpty String price) {
        this(customer, description, FastMoney.parse(price));
    }

}
