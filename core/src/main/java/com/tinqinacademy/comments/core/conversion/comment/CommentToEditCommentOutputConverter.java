package com.tinqinacademy.comments.core.conversion.comment;

import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.core.conversion.BaseConverter;
import com.tinqinacademy.comments.persistence.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToEditCommentOutputConverter extends BaseConverter<Comment, EditCommentOutput> {

    @Override
    protected EditCommentOutput convertObject(Comment source) {
        EditCommentOutput editCommentOutput = EditCommentOutput.builder()
                .id(source.getId().toString())
                .build();

        return editCommentOutput;
    }
}
