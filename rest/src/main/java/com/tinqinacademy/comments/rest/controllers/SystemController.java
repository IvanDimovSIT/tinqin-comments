package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOperation;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOperation;
import com.tinqinacademy.comments.api.RestApiRoutes;
import com.tinqinacademy.comments.core.response.ResponseEntityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SystemController {
    private final AdminEditCommentOperation adminEditCommentOperation;
    private final AdminDeleteCommentOperation adminDeleteCommentOperation;
    private final ResponseEntityMapper responseEntityMapper;

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

        Either<Errors, AdminEditCommentOutput> output = adminEditCommentOperation.process(adminEditCommentInput);
        return responseEntityMapper.mapToResponseEntity(output, HttpStatus.OK);
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

        Either<Errors, AdminDeleteCommentOutput> output = adminDeleteCommentOperation.process(input);
        return responseEntityMapper.mapToResponseEntity(output, HttpStatus.OK);
    }
}
