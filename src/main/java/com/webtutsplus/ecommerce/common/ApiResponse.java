package com.webtutsplus.ecommerce.common;

import java.time.LocalDateTime;

public class ApiResponse {
    final boolean success;
    final String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
