package com.tinqinacademy.comments.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.RestApiRoutes;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.persistence.model.Comment;
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
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
public class HotelControllerTests {
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
    public void testGetCommentsOk() throws Exception {
        Comment comment =commentsRepository.findAll().getFirst();

        mvc.perform(get(RestApiRoutes.HOTEL_GET_COMMENTS, comment.getRoomId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCommentsBadRequest() throws Exception {
        mvc.perform(get(RestApiRoutes.HOTEL_GET_COMMENTS, "  ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddCommentCreated() throws Exception {
        Comment comment = commentsRepository.findAll().getFirst();

        AddCommentInput input = AddCommentInput.builder()
                .content("Test comment")
                .authorId(UUID.randomUUID().toString())
                .roomId(comment.getRoomId().toString())
                .build();

        mvc.perform(post(RestApiRoutes.HOTEL_ADD_COMMENT, comment.getRoomId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddCommentBadRequest1() throws Exception {
        Comment comment = commentsRepository.findAll().getFirst();

        AddCommentInput input = AddCommentInput.builder()
                .content(" ")
                .authorId(UUID.randomUUID().toString())
                .roomId(comment.getRoomId().toString())
                .build();

        mvc.perform(post(RestApiRoutes.HOTEL_ADD_COMMENT, comment.getRoomId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddCommentBadRequest2() throws Exception {
        Comment comment = commentsRepository.findAll().getFirst();

        AddCommentInput input = AddCommentInput.builder()
                .content("Test comment")
                .authorId("123")
                .roomId(comment.getRoomId().toString())
                .build();

        mvc.perform(post(RestApiRoutes.HOTEL_ADD_COMMENT, comment.getRoomId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEditCommentOk() throws Exception {
        Comment comment = commentsRepository.findAll().getFirst();

        EditCommentInput input = EditCommentInput.builder()
                .content("Updated comment")
                .authorId(comment.getAuthorId().toString())
                .build();

        mvc.perform(patch(RestApiRoutes.HOTEL_EDIT_COMMENT, comment.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditCommentNotFound() throws Exception {
        EditCommentInput input = EditCommentInput.builder()
                .content("Updated comment")
                .authorId(UUID.randomUUID().toString())
                .build();

        mvc.perform(patch(RestApiRoutes.HOTEL_EDIT_COMMENT, UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEditCommentForbidden() throws Exception {
        Comment comment = commentsRepository.findAll().getFirst();

        EditCommentInput input = EditCommentInput.builder()
                .content("Updated comment")
                .authorId(UUID.randomUUID().toString())
                .build();

        mvc.perform(patch(RestApiRoutes.HOTEL_EDIT_COMMENT, comment.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isForbidden());
    }
}
