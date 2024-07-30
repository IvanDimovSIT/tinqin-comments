package com.tinqinacademy.comments.core.services.system;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOperation;
import com.tinqinacademy.comments.core.exception.exceptions.NotFoundException;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminDeleteCommentOperationProcessor implements AdminDeleteCommentOperation {
    private final CommentRepository commentRepository;

    private Comment getComment(String id) {
        return commentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Comment with id:" + id + " not found"));
    }

    @Override
    public Either<Errors, AdminDeleteCommentOutput> process(AdminDeleteCommentInput input) {
        log.info("start adminDeleteComment input:{}", input);

        Either<Errors, AdminDeleteCommentOutput> result = Try.of(() -> {
                    Comment commentToDelete = getComment(input.getCommentId());

                    commentRepository.delete(commentToDelete);

                    return AdminDeleteCommentOutput.builder()
                            .build();
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(NotFoundException.class)), Errors.builder()
                                .error(throwable.getMessage(), HttpStatus.NOT_FOUND)
                                .build()
                        ),
                        Case($(), Errors.builder()
                                .error(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)
                                .build()
                        )
                ));

        log.info("end adminDeleteComment result:{}", result);

        return result;
    }
}
