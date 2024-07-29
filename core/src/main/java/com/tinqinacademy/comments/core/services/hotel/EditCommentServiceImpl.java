package com.tinqinacademy.comments.core.services.hotel;

import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentService;
import com.tinqinacademy.comments.core.exception.exceptions.NotFoundException;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditCommentServiceImpl implements EditCommentService {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    @Override
    public EditCommentOutput process(EditCommentInput input) {
        log.info("start editComment input:{}", input);

        Comment comment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new NotFoundException("Comment with id:"+input.getCommentId()+" not found"));

        comment.setContent(input.getContent());

        Comment editedComment = commentRepository.save(comment);

        EditCommentOutput output = conversionService.convert(editedComment, EditCommentOutput.class);

        log.info("end editComment result:{}", output);

        return output;
    }
}
