package com.moneelab.assignment.domain.comment;

import java.util.List;

public interface CommentRepository {
    Long save(Comment comment);
    void update(Long commentId, String content);
    void deleteById(Long commentId);
    Comment findById(Long commentId);
    List<Comment> findCommentsByPostId(Long postId);
}
