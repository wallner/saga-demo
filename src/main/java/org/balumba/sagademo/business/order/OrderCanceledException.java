package org.balumba.sagademo.business.order;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class OrderCanceledException extends RuntimeException {
}
