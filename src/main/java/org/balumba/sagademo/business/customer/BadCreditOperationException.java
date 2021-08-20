package org.balumba.sagademo.business.customer;

public class BadCreditOperationException extends RuntimeException {

    public BadCreditOperationException(String message) {
        super(message);
    }
}

