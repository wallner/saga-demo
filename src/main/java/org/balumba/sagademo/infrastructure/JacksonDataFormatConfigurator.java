package org.balumba.sagademo.infrastructure;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.camunda.spin.spi.DataFormatConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
public class JacksonDataFormatConfigurator implements DataFormatConfigurator<JacksonJsonDataFormat> {

    @Override
    public Class<JacksonJsonDataFormat> getDataFormatClass() {
        return JacksonJsonDataFormat.class;
    }

    @Override
    public void configure(JacksonJsonDataFormat dataFormat) {
        ObjectMapper objectMapper = dataFormat.getObjectMapper();
        objectMapper.registerModule(moneyModule());
    }

    @Bean
    public Module moneyModule() {
        return new MoneyModule();
    }
}