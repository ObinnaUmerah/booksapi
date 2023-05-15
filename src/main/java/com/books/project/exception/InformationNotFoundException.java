package com.books.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {
    /**
     * An exception that is thrown whenever there is no information to be found on a requested part of the table
     * @param message A message explaining the error to the user.
     */
    public InformationNotFoundException(String message) {
        super(message);
    }
}
