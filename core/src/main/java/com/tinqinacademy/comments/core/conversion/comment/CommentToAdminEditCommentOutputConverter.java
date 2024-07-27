package com.tinqinacademy.comments.core.conversion.comment;

import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.core.conversion.BaseConverter;
import com.tinqinacademy.comments.persistence.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToAdminEditCommentOutputConverter extends BaseConverter<Comment, AdminEditCommentOutput> {
    @Override
    protected AdminEditCommentOutput convertObject(Comment source) {
        AdminEditCommentOutput adminEditCommentOutput = AdminEditCommentOutput.builder()
                .id(source.getId().toString())
                .build();

        return adminEditCommentOutput;
    }
}
