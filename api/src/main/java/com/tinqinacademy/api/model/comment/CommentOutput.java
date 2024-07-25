package com.tinqinacademy.api.model.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CommentOutput {
    @NotEmpty
    private String id;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;
    @NotEmpty
    private String content;
    @NotNull
    private LocalDate publishDate;
    @NotNull
    private LocalDate lastEditedDate;
    @NotEmpty
    private String lastEditedBy;
}
