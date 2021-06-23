package com.moneelab.assignment.service.comment;

import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;

import java.util.List;

public interface CommentService {
    Long save(CommentRequest commentRequest, Long authorId);
    void update(Long commentId, CommentRequest commentRequest);
    void delete(Long commentId);
    List<CommentResponse> findCommentsByPostId(Long postId);
}
