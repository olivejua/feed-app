package com.moneelab.assignment.service.comment;

import com.moneelab.assignment.domain.comment.Comment;
import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.exception.NotExistException;

import java.util.List;
import java.util.stream.Collectors;

import static com.moneelab.assignment.config.AppConfig.commentRepository;

public class CommentServiceImpl implements CommentService {

    /**
     * invoking a repository instance
     */
    private CommentRepository commentRepository = commentRepository();

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
    public synchronized Long save(CommentRequest commentRequest, Long authorId) {
        return commentRepository.save(commentRequest.toComment(authorId));
    }

    @Override
    public synchronized void update(Long commentId, CommentRequest commentRequest) {
        commentRepository.update(commentId, commentRequest.getContent());
    }

    @Override
    public synchronized void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public synchronized CommentResponse findById(Long commentId) throws NotExistException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistException("해당 댓글이 없습니다. id=" + commentId));

        return new CommentResponse(comment);
    }

    @Override
    public synchronized List<CommentResponse> findCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
