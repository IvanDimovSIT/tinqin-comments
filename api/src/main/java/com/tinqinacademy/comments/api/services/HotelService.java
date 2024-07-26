package com.tinqinacademy.comments.api.services;

import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOutput;

public interface HotelService {
    GetCommentsOutput getComments(final GetCommentsInput input);
    AddCommentOutput addComment(final AddCommentInput input);
    EditCommentOutput editComment(final EditCommentInput input);
}
