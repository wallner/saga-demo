package org.balumba.sagademo.infrastructure;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;

import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MonetaryAmountAttributeConverterTest {

    private final MonetaryAmountAttributeConverter converter = new MonetaryAmountAttributeConverter();

    @Test
    public void monetary_amount_gets_converted_to_string() {
        MonetaryAmount money = Money.of(10, "EUR");
        assertThat(converter.convertToDatabaseColumn(money)).isEqualTo("EUR 10.00");
    }

    @Test
    public void string_is_converted_to_monetary_Amount() {
        var conversionResult = converter.convertToEntityAttribute("EUR 10");

        assertThat(conversionResult.getCurrency().getCurrencyCode()).isEqualTo("EUR");
        assertThat(conversionResult).isEqualByComparingTo(FastMoney.of(10, "EUR"));
    }

}