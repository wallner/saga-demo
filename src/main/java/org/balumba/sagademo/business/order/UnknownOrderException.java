package org.balumba.sagademo.business.order;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class UnknownOrderException extends RuntimeException {
}
