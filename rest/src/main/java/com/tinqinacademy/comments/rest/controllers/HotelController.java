package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.api.services.HotelService;
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
public class HotelController {
    private final HotelService hotelService;

    @Operation(summary = "Retrieves comments", description = "Gets a list of comments left for a certain room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping(RestApiRoutes.HOTEL_GET_COMMENTS)
    public ResponseEntity<?> getComments(@PathVariable String roomId) {
        GetCommentsInput input = GetCommentsInput.builder()
                .roomId(roomId)
                .build();

        GetCommentsOutput output = hotelService.getComments(input);

        return new ResponseEntity<>(
                output,
                HttpStatus.OK
        );
    }

    @Operation(summary = "Leaves a comment", description = "Leaves a comment regarding a certain room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping(RestApiRoutes.HOTEL_ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable String roomId, @RequestBody @Valid AddCommentInput input) {
        AddCommentInput addCommentInput = input.toBuilder()
                .roomId(roomId)
                .build();

        AddCommentOutput output = hotelService.addComment(addCommentInput);

        return new ResponseEntity<>(
                output,
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "User edits a comment", description = "User can edit own comment left for certain room. " +
            "Last edited date is updated. Info regarding user edited is updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(RestApiRoutes.HOTEL_EDIT_COMMENT)
    public ResponseEntity<?> editComment(@PathVariable String commentId, @RequestBody @Valid EditCommentInput input) {
        EditCommentInput editCommentInput = input.toBuilder()
                .commentId(commentId)
                .build();

        EditCommentOutput output = hotelService.editComment(editCommentInput);

        return new ResponseEntity<>(
                output,
                HttpStatus.OK
        );
    }

}
