package com.tinqinacademy.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.api.RestApiRoutes;
import com.tinqinacademy.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.api.operations.hotel.editcomment.EditCommentInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension .class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetCommentsOk() throws Exception {
        mvc.perform(get(RestApiRoutes.HOTEL_GET_COMMENTS, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testAddCommentOk() throws Exception {
        AddCommentInput input = AddCommentInput.builder()
                .content("content")
                .firstName("Ivan")
                .lastName("Koleev")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.HOTEL_ADD_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddCommentBadRequest() throws Exception {
        AddCommentInput input = AddCommentInput.builder()
                .content("content")
                .firstName("a")
                .lastName("Koleev")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.HOTEL_ADD_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEditCommentOk() throws Exception {
        EditCommentInput input = EditCommentInput.builder()
                .content("content")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.HOTEL_EDIT_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testEditCommentBadRequest() throws Exception {
        EditCommentInput input = EditCommentInput.builder()
                .content("")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.HOTEL_EDIT_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                )
                .andExpect(status().isBadRequest());
    }
}
