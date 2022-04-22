package org.balumba.sagademo.business.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.balumba.sagademo.infrastructure.AbstractPersistable;
import org.javamoney.moneta.FastMoney;

import javax.money.MonetaryAmount;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static org.balumba.sagademo.business.order.OrderState.*;

@Entity
@Table(name = "order_table")
@JsonIgnoreProperties({"new"})
public final class Order extends AbstractPersistable<Long> {

    @NotBlank
    private String customer;

    @NotEmpty
    private String description;

    @NotNull
    private MonetaryAmount price;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    public Order(@NotBlank String customer, @NotEmpty String description, @NotNull String price) {
        this(customer, description, FastMoney.parse(price));
    }

    public Order(@NotBlank String customer, @NotEmpty String description, @NotNull MonetaryAmount price) {
        this.customer = customer;
        this.description = description;
        this.price = price;
        this.state = PENDING;
    }

    @Deprecated
    protected Order() {
    }

    public static Order fromDto(OrderDto dto) {
        return new Order(dto.customer(), dto.description(), dto.price());
    }

    public String getCustomer() {
        return customer;
    }

    public String getDescription() {
        return description;
    }

    public OrderState getState() {
        return state;
    }

    public void approve() {
        state = APPROVED;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public void cancel() {
        state = CANCELED;
    }

}
