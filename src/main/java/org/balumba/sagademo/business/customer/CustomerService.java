package org.balumba.sagademo.business.customer;


import org.balumba.sagademo.business.order.OrderRepository;
import org.balumba.sagademo.business.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class CustomerService {

    public static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public void reserveCredit(Long orderId) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var customer = customerRepository.findByName(order.getCustomer()).orElseThrow();
        customer.decreaseCreditLimit(order.getPrice());
        customerRepository.save(customer);
        logger.info("reserved credit with customer {} for order price of {}", customer.getName(), order.getPrice());
    }

    public void cancelReservation(Long orderId) {
        var order = orderRepository.findById(orderId).orElseThrow();
        var customer = customerRepository.findByName(order.getCustomer()).orElseThrow();
        customer.increaseCreditLimit(order.getPrice());
        customerRepository.save(customer);
        logger.info("canceled credit reservation of {} for customer {}", order.getPrice(), order.getCustomer());
    }
}
