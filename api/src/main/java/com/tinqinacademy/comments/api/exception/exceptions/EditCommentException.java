package com.tinqinacademy.comments.api.exception.exceptions;

import com.tinqinacademy.comments.api.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EditCommentException extends BaseException {
    public EditCommentException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
