package com.tinqinacademy.api.services;

import com.tinqinacademy.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.api.operations.system.admineditcomment.AdminEditCommentOutput;

public interface SystemService {
    AdminEditCommentOutput adminEditComment(final AdminEditCommentInput input);
    AdminDeleteCommentOutput adminDeleteComment(final AdminDeleteCommentInput input);
}
