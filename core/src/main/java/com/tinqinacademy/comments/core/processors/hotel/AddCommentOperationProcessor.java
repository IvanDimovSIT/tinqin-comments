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
        return Try.of(() -> {
                    log.info("Start addComment input:{}", input);
                    validate(input);
                    Comment comment = conversionService.convert(input, Comment.class);
                    Comment addedComment = commentRepository.save(comment);

                    AddCommentOutput result = conversionService.convert(addedComment, AddCommentOutput.class);
                    log.info("End addComment result:{}", result);
                    return result;
                })
                .toEither()
                .mapLeft(errorMapper::map);
    }
}
