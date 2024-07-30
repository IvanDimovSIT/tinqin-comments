package com.tinqinacademy.comments.core.services.hotel;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOperation;
import com.tinqinacademy.comments.core.exception.exceptions.NotFoundException;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditCommentOperationProcessor implements EditCommentOperation {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    private Comment getComment(String id){
        return commentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Comment with id:" + id + " not found"));
    }

    @Override
    public Either<Errors, EditCommentOutput> process(EditCommentInput input) {
        log.info("start editComment input:{}", input);

        Either<Errors, EditCommentOutput> result = Try.of(() -> {
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
