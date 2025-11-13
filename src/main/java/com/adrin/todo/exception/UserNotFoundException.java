package com.adrin.todo.exception;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
