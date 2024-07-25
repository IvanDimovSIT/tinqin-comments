package com.tinqinacademy.api.operations.system.admineditcomment;

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
public class AdminEditCommentInput {
    @JsonIgnore
    private String commentId;
    @NotEmpty
    private String roomNumber;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;
    @NotEmpty
    private String content;
}
