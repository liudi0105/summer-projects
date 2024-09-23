package com.github.liudi0105.wrapper.spring.common.dto.error;

public class AppError extends RuntimeException {
    public AppError(String message) {
        super(message);
    }

    public AppError(Throwable cause) {
        super(cause);
    }
}
