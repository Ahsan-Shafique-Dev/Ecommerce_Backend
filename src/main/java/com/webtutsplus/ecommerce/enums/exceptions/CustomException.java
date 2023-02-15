package com.webtutsplus.ecommerce.enums.exceptions;

public class CustomException extends IllegalArgumentException {
    public CustomException(String msg) {
        super(msg);
    }
}
