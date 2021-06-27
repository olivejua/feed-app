package com.moneelab.assignment.service.comment;

import com.moneelab.assignment.domain.comment.Comment;
import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.post.PostService;

import java.util.List;
import java.util.stream.Collectors;

import static com.moneelab.assignment.config.AppConfig.commentRepository;
import static com.moneelab.assignment.config.AppConfig.postService;

public class CommentServiceImpl implements CommentService {

    /**
     * invoking repository instances
     */
    private CommentRepository commentRepository = commentRepository();
    private PostService postService = postService();

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
        postService.findById(commentRequest.getPostId());
        return commentRepository.save(commentRequest.toComment(authorId));
    }

    @Override
    public synchronized void update(Long commentId, CommentRequest commentRequest) throws NotExistException{
        postService.findById(commentRequest.getPostId());
        commentRepository.update(commentId, commentRequest.getContent());
    }

    @Override
    public synchronized void delete(Long commentId) throws NotExistException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotExistException("해당 댓글이 없습니다. id=" + commentId));
        postService.findById(comment.getPostId());

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
