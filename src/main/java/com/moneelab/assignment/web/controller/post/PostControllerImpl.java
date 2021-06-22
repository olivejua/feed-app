package com.moneelab.assignment.web.controller.post;

import java.util.Map;

public class PostControllerImpl implements PostController {

    @Override
    public String save(Map<String, String> paramMap) {
        return "result save";
    }

    @Override
    public String update(Map<String, String> paramMap, String requestBody) {
        String postId = paramMap.get("postId");

        return "result update: postId: " + postId + ", requestBody: " + requestBody;
    }

    @Override
    public String delete(Map<String, String> paramMap) {
        return "result delete";
    }
}
