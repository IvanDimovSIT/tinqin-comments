package com.tinqinacademy.comments.core.services;

import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.admindeletecomment.AdminDeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.operations.system.admineditcomment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.services.SystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
    @Override
    public AdminEditCommentOutput adminEditComment(AdminEditCommentInput input) {
        log.info("start adminEditComment input:{}", input);

        AdminEditCommentOutput output = AdminEditCommentOutput.builder()
                .id(input.getCommentId())
                .build();

        log.info("end adminEditComment result:{}", output);

        return output;
    }

    @Override
    public AdminDeleteCommentOutput adminDeleteComment(AdminDeleteCommentInput input) {
        log.info("start adminDeleteComment input:{}", input);

        AdminDeleteCommentOutput output = AdminDeleteCommentOutput.builder()
                .build();

        log.info("end adminDeleteComment result:{}", output);

        return output;
    }
}
