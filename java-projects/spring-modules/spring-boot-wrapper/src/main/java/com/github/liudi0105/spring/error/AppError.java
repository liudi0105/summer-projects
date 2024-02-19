package com.github.liudi0105.spring.error;

public class AppError extends RuntimeException {
    public AppError(String message) {
        super(message);
    }

    public AppError(Throwable cause) {
        super(cause);
    }
}
