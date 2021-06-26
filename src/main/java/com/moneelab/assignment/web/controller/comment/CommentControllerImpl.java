package com.moneelab.assignment.web.controller.comment;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ErrorResponse;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.exception.NotAvailableException;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.comment.CommentService;
import com.moneelab.assignment.util.PathConstants;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.commentService;
import static com.moneelab.assignment.util.PathConstants.*;
import static javax.servlet.http.HttpServletResponse.*;

public class CommentControllerImpl implements CommentController {

    /**
     * invoking a service instance
     */
    private CommentService commentService = commentService();

    /**
     * making it Singleton
     */
    private CommentControllerImpl() {}
    private static final CommentControllerImpl instance = new CommentControllerImpl();

    public static CommentControllerImpl getInstance() {
        return instance;
    }

    /**
     * processing presentation logic
     */
    @Override
    public ResponseEntity save(CommentRequest commentRequest, SessionUserService sessionService) {
        UserResponse currentUser = sessionService.getUser();

        Long savedCommentId = commentService.save(commentRequest, currentUser.getUserId());
        return new ResponseEntity(SC_CREATED, HOST+P_COMMENT+"?id="+savedCommentId);
    }

    @Override
    public ResponseEntity update(Map<String, String> paramMap, CommentRequest commentRequest, SessionUserService sessionService) {
        Long commentId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            validateAuthor(commentId, sessionService);
            commentService.update(commentId, commentRequest);

            result = new ResponseEntity(SC_NO_CONTENT);
        } catch (NotAvailableException | NotExistException e) {
            result = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(e.getMessage()));
        }

        return result;
    }

    @Override
    public ResponseEntity delete(Map<String, String> paramMap, SessionUserService sessionService) {
        Long commentId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            validateAuthor(commentId, sessionService);
            commentService.delete(commentId);

            result = new ResponseEntity(SC_NO_CONTENT);
        } catch (NotAvailableException | NotExistException e) {
            result = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(e.getMessage()));
        }

        return result;
    }

    @Override
    public ResponseEntity findComment(Map<String, String> paramMap) {
        Long commentId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            CommentResponse comment = commentService.findById(commentId);
            result = new ResponseEntity(SC_OK, comment);
        } catch (NotExistException ne) {
            result = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(ne.getMessage()));
        }

        return result;
    }

    private void validateAuthor(Long commentId, SessionUserService sessionService) throws NotExistException, NotAvailableException {
        CommentResponse comment = commentService.findById(commentId);

        UserResponse currentUser = sessionService.getUser();
        if (!comment.getAuthorId().equals(currentUser.getUserId())) {
            throw new NotAvailableException("로그인 사용자와 댓글 작성자가 일치하지 않습니다.");
        }
    }
}