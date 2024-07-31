package com.tinqinacademy.comments.core.processors.hotel;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOperation;
import com.tinqinacademy.comments.core.errors.ErrorMapper;
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

import static io.vavr.API.*;

@Service
@Slf4j
public class AddCommentOperationProcessor extends BaseOperationProcessor implements AddCommentOperation {
    private final CommentRepository commentRepository;

    public AddCommentOperationProcessor(ConversionService conversionService, ErrorMapper errorMapper,
                                        Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<Errors, AddCommentOutput> process(AddCommentInput input) {
        log.info("start addComment input:{}", input);

        Either<Errors, AddCommentOutput> result = Try.of(() -> {
                    validate(input);
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
