package com.tinqinacademy.api.services;

import com.tinqinacademy.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.api.operations.hotel.getcomments.GetCommentsOutput;

public interface HotelService {
    GetCommentsOutput getComments(final GetCommentsInput input);
    AddCommentOutput addComment(final AddCommentInput input);
    EditCommentOutput editComment(final EditCommentInput input);
}
