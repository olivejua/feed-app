package com.moneelab.assignment.web.controller.comment;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.comment.CommentRequest;
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
    public ResponseEntity save(CommentRequest commentRequest) {
        Long savedCommentId = commentService.save(commentRequest, 0L);
        return new ResponseEntity(HttpServletResponse.SC_CREATED, savedCommentId);
    }

    @Override
    public ResponseEntity update(Map<String, String> paramMap, CommentRequest commentRequest) {
        Long commentId = Long.parseLong(paramMap.get("id"));

        commentService.update(commentId, commentRequest);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(Map<String, String> paramMap) {
        Long commentId = Long.parseLong(paramMap.get("id"));

        commentService.delete(commentId);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }
}