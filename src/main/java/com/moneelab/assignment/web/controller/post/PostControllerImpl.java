package com.moneelab.assignment.web.controller.post;

import com.moneelab.assignment.dto.post.PostRequest;

import java.util.Map;

public class PostControllerImpl implements PostController {

    @Override
    public String save(PostRequest postRequest) {
        return "result save";
    }

    @Override
    public String update(Map<String, String> paramMap, PostRequest postRequest) {
        String postId = paramMap.get("postId");

        return "result update: postId: " + postId + ", requestBody: " + postRequest;
    }

    @Override
    public String delete(Map<String, String> paramMap) {
        return "result delete";
    }
}
