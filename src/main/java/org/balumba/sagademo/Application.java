package org.balumba.sagademo;

import org.balumba.sagademo.business.customer.Customer;
import org.balumba.sagademo.business.customer.CustomerRepository;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableProcessApplication("Saga Demo")
@SpringBootApplication(scanBasePackages = {"org.balumba.sagademo", "org.camunda.bpm"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner init(CustomerRepository customerRepository) {
        return (ApplicationArguments args) -> dataSetup(customerRepository);
    }

    public void dataSetup(CustomerRepository customerRepository) {
        customerRepository.save(new Customer("Anton", "5 EUR"));
        customerRepository.save(new Customer("Berta", "10 EUR"));
        customerRepository.save(new Customer("Cesar", "100 EUR"));
    }
}
