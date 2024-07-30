package com.tinqinacademy.comments.api.operations.hotel.addcomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class AddCommentOutput implements OperationOutput {
    private String id;
}
