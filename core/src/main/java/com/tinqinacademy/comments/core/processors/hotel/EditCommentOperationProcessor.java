package com.tinqinacademy.comments.core.processors.hotel;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.exception.exceptions.EditCommentException;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOperation;
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

    private void checkUserPostedComment(Comment comment, String authorId){
        if(!comment.getAuthorId().toString().equals(authorId)){
            throw new EditCommentException("User can only edit own comments");
        }
    }

    @Override
    public Either<Errors, EditCommentOutput> process(EditCommentInput input) {
        return Try.of(() -> {
                    log.info("Start editComment input:{}", input);
                    validate(input);
                    Comment comment = getComment(input.getCommentId());
                    checkUserPostedComment(comment, input.getAuthorId());

                    comment.setContent(input.getContent());
                    comment.setLastEditedById(UUID.fromString(input.getAuthorId()));
                    Comment editedComment = commentRepository.save(comment);

                    EditCommentOutput result = conversionService.convert(editedComment, EditCommentOutput.class);
                    log.info("End editComment result:{}", result);
                    return result;
                })
                .toEither()
                .mapLeft(errorMapper::map);
    }
}
