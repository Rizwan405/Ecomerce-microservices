package com.rizwan.order.exception;

public class EntityNotFoundException extends  RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
