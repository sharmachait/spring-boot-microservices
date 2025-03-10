package com.sharmachait.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CardAlreadyExistsException extends Exception {
    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
