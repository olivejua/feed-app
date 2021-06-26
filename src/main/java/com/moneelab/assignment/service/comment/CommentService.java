package com.moneelab.assignment.service.comment;

import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.exception.NotExistException;

import java.util.List;

public interface CommentService {
    Long save(CommentRequest commentRequest, Long authorId);
    void update(Long commentId, CommentRequest commentRequest);
    void delete(Long commentId);
    CommentResponse findById(Long commentId) throws NotExistException;
    List<CommentResponse> findCommentsByPostId(Long postId);
}
