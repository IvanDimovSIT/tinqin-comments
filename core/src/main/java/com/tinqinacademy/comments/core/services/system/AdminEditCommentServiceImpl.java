package com.tinqinacademy.comments.core.services.system;

import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentService;
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
public class AdminEditCommentServiceImpl implements AdminEditCommentService {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    @Override
    public AdminEditCommentOutput process(AdminEditCommentInput input) {
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
}
