package com.springdemo.springbootrnd.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles EmptyResultDataAccessException. Triggered when resultSet returned after query is empty.
     *
     * @param ex the EmptyResultDataAccessException
     * @return the ApiError object
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleEmptyResultDataAccess(
            EmptyResultDataAccessException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, 404, "The requested resource was not found", ex);
        System.out.println("Exception occurred: "+apiError);
        return new ResponseEntity(apiError, NOT_FOUND);
    }
}
