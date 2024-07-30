package com.tinqinacademy.comments.core.services.hotel;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOperation;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddCommentOperationProcessor implements AddCommentOperation {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    @Override
    public Either<Errors, AddCommentOutput> process(AddCommentInput input) {
        log.info("start addComment input:{}", input);

        Either<Errors, AddCommentOutput> result = Try.of(() -> {
                    Comment comment = conversionService.convert(input, Comment.class);
                    Comment addedComment = commentRepository.save(comment);

                    return conversionService.convert(addedComment, AddCommentOutput.class);
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(), Errors.builder()
                                .error(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)
                                .build()
                        )
                ));
        log.info("end addComment result:{}", result);

        return result;
    }
}
