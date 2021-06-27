package com.moneelab.assignment.domain.comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Long save(Comment comment);
    void update(Long commentId, String content);
    void deleteById(Long commentId);
    void deleteByPostId(Long postId);
    Optional<Comment> findById(Long commentId);
    List<Comment> findCommentsByPostId(Long postId);
    void clearAll();
}
