package org.balumba.sagademo.business.customer;

import org.balumba.sagademo.infrastructure.AbstractPersistable;
import org.balumba.sagademo.infrastructure.Currency;
import org.javamoney.moneta.FastMoney;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Customer extends AbstractPersistable<Long> {

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private MonetaryAmount originalCreditLimit;

    @NotNull
    private MonetaryAmount creditLimit;

    public Customer(@NotNull String name, @NotNull MonetaryAmount creditLimit) {
        this.name = name;
        this.creditLimit = creditLimit;
        originalCreditLimit = creditLimit;
    }

    public Customer(@NotNull String name, @NotNull String creditLimit) {
        this(name, FastMoney.parse(creditLimit));
    }


    protected Customer() {
    }

    public String getName() {
        return name;
    }

    public void decreaseCreditLimit(MonetaryAmount amount) {
        var newCreditLimit = creditLimit.subtract(amount);
        if (newCreditLimit.isLessThan(FastMoney.zero(Currency.EURO))) {
            throw new BadCreditOperationException("Insufficient Credit");
        }
        creditLimit = newCreditLimit;
    }

    public void increaseCreditLimit(MonetaryAmount amount) {
        var newCreditLimit = creditLimit.add(amount);
        if (newCreditLimit.isGreaterThan(originalCreditLimit)) {
            throw new BadCreditOperationException("Resulting credit exceeds original limit");
        }
    }

    public void resetCreditLimit() {
        creditLimit = originalCreditLimit;
    }

    public MonetaryAmount getCreditLimit() {
        return creditLimit;
    }
}
