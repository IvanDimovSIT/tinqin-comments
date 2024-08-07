package com.tinqinacademy.comments.restexport;


import com.tinqinacademy.comments.api.RestApiRoutes;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@FeignClient(name = "hotel")
@Headers({"Content-Type: application/json"})
public interface CommentsRestExport {


    @RequestLine("GET /api/v1/hotel/{roomId}/comment")
    GetCommentsOutput getComments(@Param("roomId") String roomId);


    @RequestLine("POST /api/v1/hotel/{roomId}/comment")
    AddCommentOutput addComment(@Param("roomId") String roomId, @RequestBody AddCommentInput input);


    @RequestLine("PATCH /api/v1/hotel/comment/{commentId}")
    EditCommentOutput editComment(@Param("commentId") String commentId, @RequestBody EditCommentInput input);

    @RequestLine("PUT /api/v1/system/comment/{commentId}")
    AdminEditCommentOutput adminEditComment(
            @Param("commentId") String commentId,
            @RequestBody AdminEditCommentInput input);


    @RequestLine("DELETE /api/v1/system/comment/{commentId}")
    ResponseEntity<?> adminDeleteComment(@Param("commentId") String commentId);

}
