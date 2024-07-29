package com.tinqinacademy.comments.core.services.system;

import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentService;
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
public class AdminDeleteCommentServiceImpl implements AdminDeleteCommentService {
    private final CommentRepository commentRepository;

    @Override
    public AdminDeleteCommentOutput process(AdminDeleteCommentInput input) {
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
