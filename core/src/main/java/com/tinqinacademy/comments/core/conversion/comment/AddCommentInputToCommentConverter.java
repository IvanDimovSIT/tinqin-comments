package com.tinqinacademy.comments.core.conversion.comment;

import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.core.conversion.BaseConverter;
import com.tinqinacademy.comments.persistence.model.Comment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddCommentInputToCommentConverter extends BaseConverter<AddCommentInput, Comment> {

    @Override
    protected Comment convertObject(AddCommentInput source) {
        Comment comment = Comment.builder()
                .roomId(UUID.fromString(source.getRoomId()))
                .content(source.getContent())
                .authorId(UUID.fromString(source.getAuthorId()))
                .build();

        return comment;
    }
}
