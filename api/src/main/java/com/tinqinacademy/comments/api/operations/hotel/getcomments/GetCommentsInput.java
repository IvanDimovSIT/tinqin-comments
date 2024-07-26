package com.tinqinacademy.comments.api.operations.hotel.getcomments;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class GetCommentsInput {
    @NotEmpty
    private String roomId;
}
