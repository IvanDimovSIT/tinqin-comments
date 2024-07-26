package com.tinqinacademy.comments.api.operations.hotel.editcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class EditCommentInput {
    @JsonIgnore
    private String commentId;
    @NotEmpty
    private String content;
}
