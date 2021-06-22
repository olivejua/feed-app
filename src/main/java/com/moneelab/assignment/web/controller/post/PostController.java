package com.moneelab.assignment.web.controller.post;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.web.controller.Controller;

import java.util.Map;

public interface PostController extends Controller {

    ResponseEntity save(PostRequest postRequest);
    ResponseEntity update(Map<String, String> paramMap, PostRequest postRequest);
    ResponseEntity delete(Map<String, String> paramMap);
}
