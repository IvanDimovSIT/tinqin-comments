package com.tinqinacademy.comments.api.operations.hotel.addcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class AddCommentInput implements OperationInput {
    @JsonIgnore
    private String roomId;
    @NotEmpty
    private String authorId;
    @NotEmpty
    private String content;
}
