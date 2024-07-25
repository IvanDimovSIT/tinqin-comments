package com.tinqinacademy.api.operations.hotel.getcomments;

import com.tinqinacademy.api.model.comment.CommentOutput;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class GetCommentsOutput {
    List<CommentOutput> comments;
}
