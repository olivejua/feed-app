package com.moneelab.assignment.web.controller.comment;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.web.controller.Controller;

import java.util.Map;

public interface CommentController extends Controller {
    ResponseEntity save(CommentRequest commentRequest, SessionUserService sessionService);
    ResponseEntity update(Map<String, String> paramMap, CommentRequest commentRequest, SessionUserService sessionService);
    ResponseEntity delete(Map<String, String> paramMap, SessionUserService sessionService);
    ResponseEntity findComment(Map<String, String> paramMap);
}
