package com.moneelab.assignment.web.controller.comment;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.web.controller.Controller;

import java.util.Map;

public interface CommentController extends Controller {
    ResponseEntity save(CommentRequest commentRequest);
    ResponseEntity update(Map<String, String> paramMap, CommentRequest commentRequest);
    ResponseEntity delete(Map<String, String> paramMap);
}
