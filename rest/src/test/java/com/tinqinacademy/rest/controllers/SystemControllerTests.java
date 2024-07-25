package com.tinqinacademy.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.api.RestApiRoutes;
import com.tinqinacademy.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.api.operations.system.admineditcomment.AdminEditCommentInput;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SystemControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testAdminEditCommentOk() throws Exception {
        AdminEditCommentInput input = AdminEditCommentInput.builder()
                .content("content")
                .firstName("Filip")
                .lastName("Kovachev")
                .roomNumber("123")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.SYSTEM_ADMIN_EDIT_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testAdminEditCommentBadRequest() throws Exception {
        AdminEditCommentInput input = AdminEditCommentInput.builder()
                .content("content")
                .firstName("")
                .lastName("Kovachev")
                .roomNumber("abc")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.SYSTEM_ADMIN_EDIT_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAdminDeleteCommentOk() throws Exception {
        mvc.perform(delete(RestApiRoutes.SYSTEM_ADMIN_DELETE_COMMENT, "123")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
}
