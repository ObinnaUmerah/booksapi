package com.books.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {

    /**
     * An exception that is thrown everytime duplicate data is attempted to being added to a table.
     * @param message A message explaining the error to the user.
     */
    public InformationExistException(String message) {
        super(message);
    }
}
