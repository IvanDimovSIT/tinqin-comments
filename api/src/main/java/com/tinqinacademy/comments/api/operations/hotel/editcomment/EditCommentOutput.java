package com.tinqinacademy.comments.api.operations.hotel.editcomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class EditCommentOutput implements OperationOutput {
    private String id;
}
