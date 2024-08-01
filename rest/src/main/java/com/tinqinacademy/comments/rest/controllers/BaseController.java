package com.tinqinacademy.comments.rest.controllers;


import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.errors.Errors;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public abstract class BaseController {
    protected <T extends OperationOutput> ResponseEntity<?> mapToResponseEntity(Either<Errors, T> either, HttpStatus status) {
        return either.isRight()?
                new ResponseEntity<>(either.get(), status):
                new ResponseEntity<>(either.getLeft().getErrorInfos(), either.getLeft().getStatus());
    }
}
