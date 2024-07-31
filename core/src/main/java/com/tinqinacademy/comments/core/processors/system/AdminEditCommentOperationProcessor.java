package com.tinqinacademy.comments.core.processors.system;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOperation;
import com.tinqinacademy.comments.core.errors.ErrorMapper;
import com.tinqinacademy.comments.core.exception.exceptions.NotFoundException;
import com.tinqinacademy.comments.core.processors.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Service
@Slf4j
public class AdminEditCommentOperationProcessor extends BaseOperationProcessor implements AdminEditCommentOperation {
    private final CommentRepository commentRepository;

    public AdminEditCommentOperationProcessor(ConversionService conversionService, ErrorMapper errorMapper,
                                              Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    private Comment getComment(String id) {
        return commentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Comment with id:" + id + " not found"));
    }

    @Override
    public Either<Errors, AdminEditCommentOutput> process(AdminEditCommentInput input) {
        log.info("start adminEditComment input:{}", input);

        Either<Errors, AdminEditCommentOutput> result = Try.of(() -> {
                    validate(input);
                    Comment commentToEdit = getComment(input.getCommentId());
                    commentToEdit.setContent(input.getContent());
                    //TODO: set lastEditedBy
                    //TODO: set userId
                    //TODO: find room by room number and set roomId

                    Comment editedComment = commentRepository.save(commentToEdit);
                    return conversionService.convert(editedComment, AdminEditCommentOutput.class);
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

        log.info("end adminEditComment result:{}", result);

        return result;
    }
}
