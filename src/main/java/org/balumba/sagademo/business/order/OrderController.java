package org.balumba.sagademo.business.order;


import com.fasterxml.jackson.annotation.JsonFilter;
import org.balumba.sagademo.saga.OrderSaga;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderSaga orderSaga;
    private final OrderService service;

    public OrderController(OrderSaga orderSaga, OrderService service) {
        this.orderSaga = orderSaga;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto order, HttpServletResponse response) {
        Long idOfNewOrder = orderSaga.placeOrder(order);
        return ResponseEntity.created(selfLink(idOfNewOrder)).build();

    }

    @GetMapping("/{orderId}")
    public HttpEntity<?> findById(@PathVariable Long orderId, HttpServletResponse response) {
        return service.findOrderById(orderId)
                .map(this::buildEntity)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<EntityModel<Order>> buildEntity(Order order) {
        var model = EntityModel.of(order);
        model.add(Link.of(selfLink().toASCIIString()));
        return ResponseEntity.ok(model);
    }

    private URI selfLink() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .build().toUri();
    }

    private URI selfLink(Long orderId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{orderId}")
                .buildAndExpand(orderId).toUri();
    }
}