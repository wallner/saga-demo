package org.balumba.sagademo.business.customer;

import org.balumba.sagademo.business.order.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
        Optional<Customer> findByName(String name);
}
