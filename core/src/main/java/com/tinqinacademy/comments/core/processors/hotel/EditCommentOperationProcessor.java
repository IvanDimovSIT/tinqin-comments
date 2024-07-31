package com.tinqinacademy.comments.core.processors.hotel;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOperation;
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
public class EditCommentOperationProcessor extends BaseOperationProcessor implements EditCommentOperation {
    private final CommentRepository commentRepository;

    public EditCommentOperationProcessor(ConversionService conversionService, ErrorMapper errorMapper,
                                         Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    private Comment getComment(String id){
        return commentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Comment with id:" + id + " not found"));
    }

    @Override
    public Either<Errors, EditCommentOutput> process(EditCommentInput input) {
        log.info("start editComment input:{}", input);

        Either<Errors, EditCommentOutput> result = Try.of(() -> {
                    validate(input);
                    Comment comment = getComment(input.getCommentId());
                    comment.setContent(input.getContent());
                    Comment editedComment = commentRepository.save(comment);

                    return conversionService.convert(editedComment, EditCommentOutput.class);
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

        log.info("end editComment result:{}", result);

        return result;
    }
}
