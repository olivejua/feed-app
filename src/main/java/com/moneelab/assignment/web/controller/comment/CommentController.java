package com.moneelab.assignment.web.controller.comment;

import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.web.controller.Controller;

import java.util.Map;

public interface CommentController extends Controller {
    String save(CommentRequest commentRequest);
    String update(Map<String, String> paramMap, CommentRequest commentRequest);
    String delete(Map<String, String> paramMap);
}
