package com.springdemo.springbootrnd.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

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

    /**
     * Handles AuthenticationException. Triggered when jwt token is not valid.
     *
     * @param ex the AuthenticationException
     * @return the ApiError object
     */
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleExpiredJwt(
            AuthenticationException ex) {
        ApiError apiError = new ApiError(UNAUTHORIZED, 401, "Missing Authorization Header", ex);
        System.out.println("Exception occurred: "+apiError);
        return new ResponseEntity(apiError, UNAUTHORIZED);
    }
}
