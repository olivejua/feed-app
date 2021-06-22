package com.moneelab.assignment.web.controller.post;

import com.moneelab.assignment.web.controller.Controller;

import java.util.Map;

public interface PostController extends Controller {

    String save(Map<String, String> paramMap);
    String update(Map<String, String> paramMap, String requestBody);
    String delete(Map<String, String> paramMap);
}
