package com.tinqinacademy.comments.core.conversion.comment;

import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.core.conversion.BaseConverter;
import com.tinqinacademy.comments.persistence.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToAddCommentOutputConverter extends BaseConverter<Comment, AddCommentOutput> {

    @Override
    protected AddCommentOutput convertObject(Comment source) {
        AddCommentOutput output = AddCommentOutput.builder()
                .id(source.getId().toString())
                .build();

        return output;
    }
}
