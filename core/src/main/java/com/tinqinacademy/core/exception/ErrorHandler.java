package com.tinqinacademy.core.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorHandler {
    ErrorWrapper handle(final MethodArgumentNotValidException exception);
}
