package com.tinqinacademy.comments.api.operations.system.admineditcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class AdminEditCommentInput implements OperationInput {
    @JsonIgnore
    private String commentId;
    @NotEmpty
    private String adminId;
    @NotEmpty
    private String roomId;
    @NotEmpty
    private String content;
}
