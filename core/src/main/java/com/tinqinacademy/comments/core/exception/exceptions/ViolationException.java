package com.tinqinacademy.comments.core.exception.exceptions;

import com.tinqinacademy.comments.core.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ViolationException extends BaseException {
    private final List<String> errors;

    public ViolationException(List<String> errors) {
        super("ViolationException", HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }
}
