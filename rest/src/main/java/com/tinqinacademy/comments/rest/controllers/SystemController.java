package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentService;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentService;
import com.tinqinacademy.comments.api.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SystemController {
    private final AdminEditCommentService adminEditCommentService;
    private final AdminDeleteCommentService adminDeleteCommentService;

    @Operation(summary = "Admin edits a comment", description = "Admin edits a certain comment left for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PutMapping(RestApiRoutes.SYSTEM_ADMIN_EDIT_COMMENT)
    public ResponseEntity<?> adminEditComment(
            @PathVariable String commentId,
            @RequestBody @Valid AdminEditCommentInput input) {
        AdminEditCommentInput adminEditCommentInput = input.toBuilder()
                .commentId(commentId)
                .build();

        AdminEditCommentOutput output = adminEditCommentService.process(adminEditCommentInput);

        return new ResponseEntity<>(
                output,
                HttpStatus.OK
        );
    }


    @Operation(summary = "Admin deletes a comments", description = "Gets a list of comments left for a certain room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping(RestApiRoutes.SYSTEM_ADMIN_DELETE_COMMENT)
    public ResponseEntity<?> adminDeleteComment(@PathVariable String commentId) {
        AdminDeleteCommentInput input = AdminDeleteCommentInput.builder()
                .commentId(commentId)
                .build();

        AdminDeleteCommentOutput output = adminDeleteCommentService.process(input);

        return new ResponseEntity<>(
                output,
                HttpStatus.OK
        );
    }
}
