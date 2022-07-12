package com.springdemo.springbootrnd.exception;

public class UserAlreadyExistException
        extends RuntimeException {
    public UserAlreadyExistException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
