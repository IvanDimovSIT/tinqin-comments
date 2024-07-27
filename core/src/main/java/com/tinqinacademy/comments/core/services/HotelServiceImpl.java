package com.tinqinacademy.comments.core.services;

import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.api.services.HotelService;
import com.tinqinacademy.comments.core.exception.exceptions.NotFoundException;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    @Override
    public GetCommentsOutput getComments(GetCommentsInput input) {
        log.info("start getComments input:{}", input);

        List<Comment> comments = commentRepository.findAllByRoomId(UUID.fromString(input.getRoomId()));

        GetCommentsOutput output = conversionService.convert(comments, GetCommentsOutput.class);

        log.info("end getComments result:{}", output);
        return output;
    }

    @Override
    public AddCommentOutput addComment(AddCommentInput input) {
        log.info("start addComment input:{}", input);


        Comment comment = conversionService.convert(input, Comment.class);
        Comment addedComment = commentRepository.save(comment);

        AddCommentOutput output = conversionService.convert(addedComment, AddCommentOutput.class);
        log.info("end addComment result:{}", output);

        return output;
    }

    @Override
    public EditCommentOutput editComment(EditCommentInput input) {
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
