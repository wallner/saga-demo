package org.balumba.sagademo.business.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    public static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }


    public Long createOrder(Order order) {
        logger.info("creating initial order");
        return repository.save(order).getId();
    }

    public Optional<Order> findOrderById(Long orderId) {
        return repository.findById(orderId);
    }

    public Order approveOrder(Long orderId) {
        var order = repository.findById(orderId).orElseThrow();
        order.approve();
        logger.info("Approving order {}", order.getId());
        return repository.save(order);
    }

    public Order refuseOrder(Long orderId) {
        var order = repository.findById(orderId).orElseThrow();
        order.cancel();
        logger.info("Canceling order {}", order.getId());
        return repository.save(order);
    }
}
