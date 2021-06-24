package com.moneelab.assignment.web.controller.comment;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.service.comment.CommentService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.commentService;

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
    //TODO 사용자인증 구현하면 세션으로 로그인사용자 주입하기
    @Override
    public ResponseEntity save(CommentRequest commentRequest, SessionUserService sessionService) {
        UserResponse currentUser = sessionService.getUser();

        Long savedCommentId = commentService.save(commentRequest, currentUser.getUserId());
        return new ResponseEntity(HttpServletResponse.SC_CREATED, savedCommentId);
    }

    @Override
    public ResponseEntity update(Map<String, String> paramMap, CommentRequest commentRequest, SessionUserService sessionService) {
        Long commentId = Long.parseLong(paramMap.get("id"));

        validateAuthor(commentId, sessionService);
        commentService.update(commentId, commentRequest);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(Map<String, String> paramMap, SessionUserService sessionService) {
        Long commentId = Long.parseLong(paramMap.get("id"));

        validateAuthor(commentId, sessionService);
        commentService.delete(commentId);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }

    private void validateAuthor(Long commentId, SessionUserService sessionService) {
        CommentResponse comment = commentService.findById(commentId);

        UserResponse currentUser = sessionService.getUser();
        if (!comment.getAuthorId().equals(currentUser.getUserId())) {
            //TODO 에러
        }
    }
}