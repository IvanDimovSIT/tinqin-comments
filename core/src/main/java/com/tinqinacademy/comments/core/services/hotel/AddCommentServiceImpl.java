package com.tinqinacademy.comments.core.services.hotel;

import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.addcomment.AddCommentService;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddCommentServiceImpl implements AddCommentService {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    @Override
    public AddCommentOutput process(AddCommentInput input) {
        log.info("start addComment input:{}", input);

        Comment comment = conversionService.convert(input, Comment.class);
        Comment addedComment = commentRepository.save(comment);

        AddCommentOutput output = conversionService.convert(addedComment, AddCommentOutput.class);
        log.info("end addComment result:{}", output);

        return output;
    }
}
