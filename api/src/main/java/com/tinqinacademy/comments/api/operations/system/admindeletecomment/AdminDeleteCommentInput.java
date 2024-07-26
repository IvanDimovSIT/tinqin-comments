package com.tinqinacademy.comments.api.operations.system.admindeletecomment;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class AdminDeleteCommentInput {
    @NotEmpty
    private String commentId;
}
