package com.webtutsplus.ecommerce.enums.exceptions;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
