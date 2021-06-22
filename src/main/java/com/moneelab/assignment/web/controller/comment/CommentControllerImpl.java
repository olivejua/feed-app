package com.moneelab.assignment.web.controller.comment;

import com.moneelab.assignment.dto.comment.CommentRequest;

import java.util.Map;

public class CommentControllerImpl implements CommentController {
    @Override
    public String save(CommentRequest commentRequest) {
        return "result save";
    }

    @Override
    public String update(Map<String, String> paramMap, CommentRequest commentRequest) {
        return "result update";
    }

    @Override
    public String delete(Map<String, String> paramMap) {
        return "result delete";
    }
}