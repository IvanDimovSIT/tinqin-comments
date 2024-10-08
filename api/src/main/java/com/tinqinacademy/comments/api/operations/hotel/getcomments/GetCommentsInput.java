package com.tinqinacademy.comments.api.operations.hotel.getcomments;

import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class GetCommentsInput implements OperationInput {
    @NotEmpty
    private String roomId;
}
