package com.tinqinacademy.comments.core.processors.system;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOperation;
import com.tinqinacademy.comments.core.errors.ErrorMapper;
import com.tinqinacademy.comments.api.exception.exceptions.NotFoundException;
import com.tinqinacademy.comments.core.processors.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AdminDeleteCommentOperationProcessor extends BaseOperationProcessor implements AdminDeleteCommentOperation {
    private final CommentRepository commentRepository;

    public AdminDeleteCommentOperationProcessor(ConversionService conversionService, ErrorMapper errorMapper,
                                                Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    private Comment getComment(String id) {
        return commentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Comment with id:" + id + " not found"));
    }

    @Override
    public Either<Errors, AdminDeleteCommentOutput> process(AdminDeleteCommentInput input) {
        return Try.of(() -> {
                    log.info("start adminDeleteComment input:{}", input);
                    validate(input);
                    Comment commentToDelete = getComment(input.getCommentId());

                    commentRepository.delete(commentToDelete);

                    AdminDeleteCommentOutput result = AdminDeleteCommentOutput.builder()
                            .build();
                    log.info("end adminDeleteComment result:{}", result);
                    return result;
                })
                .toEither()
                .mapLeft(errorMapper::map);
    }
}
