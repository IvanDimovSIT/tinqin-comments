package com.tinqinacademy.comments.api.operations.hotel.getcomments;

import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.model.comment.CommentOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class GetCommentsOutput implements OperationOutput {
    List<CommentOutput> comments;
}
