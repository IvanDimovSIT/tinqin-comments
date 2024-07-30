package com.tinqinacademy.comments.api.operations.hotel.editcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class EditCommentInput implements OperationInput {
    @JsonIgnore
    private String commentId;
    @NotEmpty
    private String content;
}
