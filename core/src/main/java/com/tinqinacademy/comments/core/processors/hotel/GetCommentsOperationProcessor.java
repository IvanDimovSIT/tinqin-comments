package com.tinqinacademy.comments.core.processors.hotel;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOperation;
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

import java.util.List;
import java.util.UUID;

import static io.vavr.API.*;

@Service
@Slf4j
public class GetCommentsOperationProcessor extends BaseOperationProcessor implements GetCommentsOperation {
    private final CommentRepository commentRepository;

    public GetCommentsOperationProcessor(ConversionService conversionService, ErrorMapper errorMapper, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<Errors, GetCommentsOutput> process(GetCommentsInput input) {
        return Try.of(() -> {
                    log.info("start getComments input:{}", input);
                    validate(input);
                    List<Comment> comments = commentRepository.findAllByRoomId(UUID.fromString(input.getRoomId()));

                    GetCommentsOutput result = conversionService.convert(comments, GetCommentsOutput.class);
                    log.info("end getComments result:{}", result);
                    return result;
                })
                .toEither()
                .mapLeft(errorMapper::map);
    }
}
