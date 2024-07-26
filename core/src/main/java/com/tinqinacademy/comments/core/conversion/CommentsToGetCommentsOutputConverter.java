package com.tinqinacademy.comments.core.conversion;

import com.tinqinacademy.comments.api.model.comment.CommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.persistence.model.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentsToGetCommentsOutputConverter extends BaseConverter<List<Comment>, GetCommentsOutput> {
    @Override
    protected GetCommentsOutput convertObject(List<Comment> source) {
        List<CommentOutput> comments = source.stream()
                .map(comment -> CommentOutput.builder()
                        .id(comment.getId().toString())
                        .content(comment.getContent())
                        .lastEditedBy(comment.getLastEditedById().toString())
                        .lastEditedDate(comment.getLastModified().toLocalDate())
                        .publishDate(comment.getCreated().toLocalDate())
                        .firstName(comment.getAuthorId().toString())
                        .lastName(comment.getAuthorId().toString())
                        .build())
                .toList();

        GetCommentsOutput output = GetCommentsOutput.builder()
                .comments(comments)
                .build();

        return output;
    }
}
