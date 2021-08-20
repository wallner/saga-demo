package org.balumba.sagademo.business.order;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends CrudRepository<Order, Long> {}
