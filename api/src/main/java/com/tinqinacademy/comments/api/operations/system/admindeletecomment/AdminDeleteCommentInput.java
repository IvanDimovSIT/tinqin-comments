package com.tinqinacademy.comments.api.operations.system.admindeletecomment;

import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class AdminDeleteCommentInput implements OperationInput {
    @NotEmpty
    private String commentId;
}
