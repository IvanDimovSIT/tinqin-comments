package com.tinqinacademy.comments.core.services.hotel;

import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetCommentsService;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetCommentsServiceImpl implements GetCommentsService {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    @Override
    public GetCommentsOutput process(GetCommentsInput input) {
        log.info("start getComments input:{}", input);

        List<Comment> comments = commentRepository.findAllByRoomId(UUID.fromString(input.getRoomId()));

        GetCommentsOutput output = conversionService.convert(comments, GetCommentsOutput.class);

        log.info("end getComments result:{}", output);
        return output;
    }
}
