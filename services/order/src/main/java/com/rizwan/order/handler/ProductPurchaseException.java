package com.rizwan.order.handler;

public class ProductPurchaseException extends RuntimeException{
    public ProductPurchaseException(String message ) {
        super(message);
    }
}
