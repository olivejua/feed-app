package com.moneelab.assignment.service.comment;

import com.moneelab.assignment.domain.comment.Comment;
import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.domain.comment.CommentRepositoryImpl;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;

import java.util.List;
import java.util.stream.Collectors;

public class CommentServiceImpl implements CommentService {

    /**
     * invoking a repository instance
     */
    private CommentRepository commentRepository = CommentRepositoryImpl.getInstance();

    /**
     * making it Singleton
     */
    private CommentServiceImpl() {}
    private static final CommentServiceImpl instance = new CommentServiceImpl();

    public static CommentServiceImpl getInstance() {
        return instance;
    }

    /**
     * processing business logic
     */
    @Override
    public Long save(CommentRequest commentRequest, Long authorId) {
        return commentRepository.save(commentRequest.toComment(authorId));
    }

    @Override
    public void update(Long commentId, CommentRequest commentRequest) {
        commentRepository.update(commentId, commentRequest.getContent());
    }

    @Override
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentResponse> findCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
