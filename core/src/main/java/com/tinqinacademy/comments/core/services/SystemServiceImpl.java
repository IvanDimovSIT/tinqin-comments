package com.tinqinacademy.comments.core.services;

import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.services.SystemService;
import com.tinqinacademy.comments.core.exception.exceptions.NotFoundException;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
    private final ConversionService conversionService;
    private final CommentRepository commentRepository;

    @Override
    public AdminEditCommentOutput adminEditComment(AdminEditCommentInput input) {
        log.info("start adminEditComment input:{}", input);

        Comment commentToEdit = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new NotFoundException("Comment with id:"+input.getCommentId()+" not found"));
        commentToEdit.setContent(input.getContent());
        //TODO: set lastEditedBy
        //TODO: set userId
        //TODO: find room by room number and set roomId

        Comment editedComment = commentRepository.save(commentToEdit);
        AdminEditCommentOutput output = conversionService.convert(editedComment, AdminEditCommentOutput.class);

        log.info("end adminEditComment result:{}", output);

        return output;
    }

    @Override
    public AdminDeleteCommentOutput adminDeleteComment(AdminDeleteCommentInput input) {
        log.info("start adminDeleteComment input:{}", input);

        UUID commentId = UUID.fromString(input.getCommentId());
        Comment commentToDelete = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment with id:"+input.getCommentId()+" not found"));

        commentRepository.delete(commentToDelete);

        AdminDeleteCommentOutput output = AdminDeleteCommentOutput.builder()
                .build();

        log.info("end adminDeleteComment result:{}", output);

        return output;
    }
}
