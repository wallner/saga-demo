package org.balumba.sagademo.infrastructure;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

public class Currency {
    public static CurrencyUnit EURO = Monetary.getCurrency("EUR");

    private Currency() {
    }
}
