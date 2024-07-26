package com.tinqinacademy.comments.api.operations.system.admineditcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AdminEditCommentInput {
    @JsonIgnore
    private String commentId;
    @NotEmpty
    @Pattern(regexp = "[0-9]{1,5}[A-Z]?")
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
