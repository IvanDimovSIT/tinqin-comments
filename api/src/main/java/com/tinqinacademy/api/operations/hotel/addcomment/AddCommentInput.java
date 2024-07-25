package com.tinqinacademy.api.operations.hotel.addcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
public class AddCommentInput {
    @JsonIgnore
    private String roomId;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;
    @NotEmpty
    private String content;
}
