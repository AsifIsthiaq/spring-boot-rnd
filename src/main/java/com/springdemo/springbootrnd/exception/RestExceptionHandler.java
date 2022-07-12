package com.springdemo.springbootrnd.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        System.out.println("Exception occurred: " + apiError);
        return new ResponseEntity(apiError, NOT_FOUND);
    }

    /**
     * Handles UsernameNotFoundException. Triggered when username is not found.
     *
     * @param ex the UsernameNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFound(
            UsernameNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, 404, "User not found", ex);
        System.out.println("Exception occurred: " + apiError);
        return new ResponseEntity(apiError, NOT_FOUND);
    }

    /**
     * Handles BadCredentialsException. Triggered when username or password is incorrect.
     *
     * @param ex the BadCredentialsException
     * @return the ApiError object
     */
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentials(
            BadCredentialsException ex) {
        ApiError apiError = new ApiError(FORBIDDEN, 403, "Incorrect username or password", ex);
        System.out.println("Exception occurred: " + apiError);
        return new ResponseEntity(apiError, FORBIDDEN);
    }

    /**
     * Handles AuthenticationException. Triggered when jwt token is not valid.
     *
     * @param ex the AuthenticationException
     * @return the ApiError object
     */
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleExpiredJwtException(
            AuthenticationException ex) {
        ApiError apiError = new ApiError(UNAUTHORIZED, 401, "Invalid token", ex);
        System.out.println("Exception occurred: " + apiError);
        return new ResponseEntity(apiError, UNAUTHORIZED);
    }

    /**
     * Handles UserAlreadyExistException. Triggered when trying to register a user with a username that already exists.
     *
     * @param ex the UserAlreadyExistException
     * @return the ApiError object
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    protected ResponseEntity<Object> UserAlreadyExistException(
            UserAlreadyExistException ex) {
        ApiError apiError = new ApiError(OK, 200, "Username already exists", ex);
        System.out.println("Exception occurred: " + apiError);
        return new ResponseEntity(apiError, OK);
    }
}
