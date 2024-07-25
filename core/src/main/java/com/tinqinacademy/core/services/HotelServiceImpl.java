package com.tinqinacademy.core.services;

import com.tinqinacademy.api.model.comment.CommentOutput;
import com.tinqinacademy.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.api.operations.hotel.getcomments.GetCommentsOutput;
import com.tinqinacademy.api.services.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Override
    public GetCommentsOutput getComments(GetCommentsInput input) {
        log.info("start getComments input:{}", input);

        CommentOutput comment = CommentOutput.builder()
                .id("123")
                .firstName("Thomas")
                .lastName("Jackson")
                .content("Comment about room " + input.getRoomId())
                .lastEditedDate(LocalDate.now())
                .publishDate(LocalDate.now())
                .lastEditedBy("visitor")
                .build();

        GetCommentsOutput output = GetCommentsOutput.builder()
                .comments(List.of(comment))
                .build();

        log.info("end getComments result:{}", output);
        return output;
    }

    @Override
    public AddCommentOutput addComment(AddCommentInput input) {
        log.info("start addComment input:{}", input);

        AddCommentOutput output = AddCommentOutput.builder()
                .id("123")
                .build();

        log.info("end addComment result:{}", output);

        return output;
    }

    @Override
    public EditCommentOutput editComment(EditCommentInput input) {
        log.info("start editComment input:{}", input);

        EditCommentOutput output = EditCommentOutput.builder()
                .id("123")
                .build();

        log.info("end editComment result:{}", output);

        return output;
    }
}
