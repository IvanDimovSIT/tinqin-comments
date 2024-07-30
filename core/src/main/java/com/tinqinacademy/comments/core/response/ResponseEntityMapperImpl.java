package com.tinqinacademy.comments.core.response;


import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.errors.Errors;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseEntityMapperImpl implements ResponseEntityMapper {


    @Override
    public <T extends OperationOutput> ResponseEntity<?> mapToResponseEntity(Either<Errors, T> either, HttpStatus status) {
        log.info("Start mapToResponseEntity input: {}, status: {}", either, status);
        ResponseEntity<?> result;
        if (either.isRight()) {
            result = new ResponseEntity<>(either.get(), status);
        }else{
            Errors errors = either.getLeft();
            result = new ResponseEntity<>(errors.getErrorInfos(), errors.getStatus());
        }

        log.info("End mapToResponseEntity result: {}", result);

        return result;
    }
}