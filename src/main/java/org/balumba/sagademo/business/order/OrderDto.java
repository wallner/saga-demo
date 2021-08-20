package org.balumba.sagademo.business.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.javamoney.moneta.FastMoney;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class OrderDto {

    @NotBlank
    private final String customer;

    @NotEmpty
    private final String description;

    @NotNull
    private final MonetaryAmount price;

    @JsonCreator
    public OrderDto(@NotBlank String customer, @NotEmpty String description, @NotNull MonetaryAmount price) {
        this.customer = customer;
        this.description = description;
        this.price = price;
    }

    public OrderDto(@NotBlank String customer, @NotEmpty String description, @NotEmpty String price) {
        this(customer, description, FastMoney.parse(price));
    }

    public String getCustomer() {
        return customer;
    }

    public String getDescription() {
        return description;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

}
