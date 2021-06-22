package com.moneelab.assignment.web.controller.post;

import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.web.controller.Controller;

import java.util.Map;

public interface PostController extends Controller {

    String save(PostRequest postRequest);
    String update(Map<String, String> paramMap, PostRequest postRequest);
    String delete(Map<String, String> paramMap);
}
