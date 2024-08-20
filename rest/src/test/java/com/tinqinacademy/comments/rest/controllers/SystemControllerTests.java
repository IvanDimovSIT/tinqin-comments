package com.tinqinacademy.comments.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.RestApiRoutes;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
public class SystemControllerTests {
    @Autowired
    private CommentRepository commentsRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;


    @BeforeEach
    public void setup() {
        Comment comment = Comment.builder()
                .roomId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .content("Test example content")
                .build();

        commentsRepository.save(comment);
    }

    @AfterEach
    public void teardown() {
        commentsRepository.deleteAll();
    }

    @Test
    public void testAdminEditCommentOk() throws Exception {
        Comment comment =commentsRepository.findAll().getFirst();

        AdminEditCommentInput input = AdminEditCommentInput.builder()
                .adminId(UUID.randomUUID().toString())
                .content("Admin example content")
                .roomId(comment.getRoomId().toString())
                .build();

        mvc.perform(put(RestApiRoutes.SYSTEM_ADMIN_EDIT_COMMENT, comment.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAdminEditCommentNotFound() throws Exception {
        Comment comment =commentsRepository.findAll().getFirst();

        AdminEditCommentInput input = AdminEditCommentInput.builder()
                .adminId(UUID.randomUUID().toString())
                .content("Admin example content")
                .roomId(comment.getRoomId().toString())
                .build();

        mvc.perform(put(RestApiRoutes.SYSTEM_ADMIN_EDIT_COMMENT, UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAdminEditCommentBadRequest() throws Exception {
        Comment comment =commentsRepository.findAll().getFirst();

        AdminEditCommentInput input = AdminEditCommentInput.builder()
                .adminId(UUID.randomUUID().toString())
                .content("")
                .roomId(UUID.randomUUID().toString())
                .build();

        mvc.perform(put(RestApiRoutes.SYSTEM_ADMIN_EDIT_COMMENT, comment.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAdminDeleteCommentOk() throws Exception {
        Comment comment =commentsRepository.findAll().getFirst();

        mvc.perform(delete(RestApiRoutes.SYSTEM_ADMIN_DELETE_COMMENT, comment.getId().toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAdminDeleteCommentNotFound() throws Exception {
        mvc.perform(delete(RestApiRoutes.SYSTEM_ADMIN_DELETE_COMMENT, UUID.randomUUID().toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAdminDeleteCommentBadRequest() throws Exception {
        mvc.perform(delete(RestApiRoutes.SYSTEM_ADMIN_DELETE_COMMENT, "234"))
                .andExpect(status().isBadRequest());
    }
}
