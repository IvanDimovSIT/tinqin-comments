package com.tinqinacademy.comments.core.processors.system;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOperation;
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
        return Try.of(() -> {
                    log.info("Start adminEditComment input:{}", input);
                    validate(input);
                    Comment commentToEdit = getComment(input.getCommentId());
                    commentToEdit.setContent(input.getContent());
                    commentToEdit.setLastEditedById(UUID.fromString(input.getAdminId()));
                    commentToEdit.setRoomId(UUID.fromString(input.getRoomId()));

                    Comment editedComment = commentRepository.save(commentToEdit);
                    AdminEditCommentOutput result = conversionService.convert(editedComment, AdminEditCommentOutput.class);
                    log.info("End adminEditComment result:{}", result);

                    return result;
                })
                .toEither()
                .mapLeft(errorMapper::map);
    }
}
