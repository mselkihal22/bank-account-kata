package com.katas.bankaccountkata.exceptions;

public class OperationException extends RuntimeException {

    public OperationException(final String message) {
        super(message);
    }
}
