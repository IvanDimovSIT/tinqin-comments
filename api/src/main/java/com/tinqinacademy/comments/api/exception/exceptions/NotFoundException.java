package com.tinqinacademy.comments.api.exception.exceptions;


import com.tinqinacademy.comments.api.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
