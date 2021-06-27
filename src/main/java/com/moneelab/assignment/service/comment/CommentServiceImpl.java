package com.moneelab.assignment.service.comment;

import com.moneelab.assignment.config.AppConfig;
import com.moneelab.assignment.domain.comment.Comment;
import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.post.PostService;
import com.moneelab.assignment.service.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.moneelab.assignment.config.AppConfig.*;
import static com.moneelab.assignment.config.AppConfig.commentRepository;
import static com.moneelab.assignment.config.AppConfig.postService;

public class CommentServiceImpl implements CommentService {

    /**
     * invoking repository instances
     */
    private CommentRepository commentRepository = commentRepository();
    private PostService postService = postService();
    private UserService userService = userService();

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
    public synchronized Long save(CommentRequest commentRequest, Long authorId) throws NotExistException {
        validate(commentRequest.getPostId(), authorId);

        return commentRepository.save(commentRequest.toComment(authorId));
    }

    @Override
    public synchronized void update(Long commentId, CommentRequest commentRequest) throws NotExistException{
        validate(commentRequest.getPostId());

        commentRepository.update(commentId, commentRequest.getContent());
    }

    @Override
    public synchronized void delete(Long commentId) throws NotExistException {
        validate(commentRepository.findById(commentId), commentId);

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

    private void validate(Long postId) throws NotExistException {
        postService.findById(postId);
    }

    private void validate(Long postId, Long authorId) throws NotExistException {
        validate(postId);
        userService.findById(authorId);
    }

    private void validate(Optional<Comment> optionalComment, Long commentId) throws NotExistException {
        Comment comment = optionalComment
                .orElseThrow(() -> new NotExistException("해당 댓글이 없습니다. id="+commentId));

        validate(comment.getPostId(), comment.getAuthorId());

    }
}
