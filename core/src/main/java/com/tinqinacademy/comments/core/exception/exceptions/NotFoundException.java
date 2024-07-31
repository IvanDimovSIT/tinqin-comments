package com.tinqinacademy.comments.core.exception.exceptions;


import com.tinqinacademy.comments.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
