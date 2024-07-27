package com.tinqinacademy.comments.persistence.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "author_id", nullable = false)
    private UUID authorId;

    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @Column(name = "content", nullable = false, length = 1024, columnDefinition = "TEXT")
    private String content;

    @Column(name = "last_edited_by_id", nullable = true)
    private UUID lastEditedById;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "last_modified")
    private LocalDateTime lastModified;
}
