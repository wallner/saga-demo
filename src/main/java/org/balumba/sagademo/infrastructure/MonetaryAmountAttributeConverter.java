package org.balumba.sagademo.infrastructure;


import org.javamoney.moneta.FastMoney;

import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;
import java.util.Optional;

@Converter(autoApply = true)
public class MonetaryAmountAttributeConverter implements AttributeConverter<MonetaryAmount, String> {

    private static final MonetaryAmountFormat FORMAT = MonetaryFormats.getAmountFormat(Locale.ROOT);

    @Override
    public String convertToDatabaseColumn(MonetaryAmount amount) {
        return Optional.ofNullable(amount).map(Object::toString).orElse(null);
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String source) {

        if (source == null) {
            return null;
        }

        try {
            return FastMoney.parse(source);
        } catch (RuntimeException e) {

            try {
                return FastMoney.parse(source, FORMAT);
            } catch (RuntimeException inner) {

                // Propagate the original exception in case the fallback fails
                throw e;
            }
        }
    }
}
