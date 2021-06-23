package com.moneelab.assignment.web.controller.post;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.service.post.PostService;
import com.moneelab.assignment.service.post.PostServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PostControllerImpl implements PostController {

    /**
     * invoking a service instance
     */
    private PostService postService = PostServiceImpl.getInstance();

    /**
     * making it Singleton
     */
    private PostControllerImpl() {}
    private static final PostControllerImpl instance = new PostControllerImpl();

    public static PostControllerImpl getInstance() {
        return instance;
    }

    /**
     * processing presentation logic
     */
    @Override
    public ResponseEntity save(PostRequest postRequest) {
        Long savedPostId = postService.save(postRequest, 0L);
        return new ResponseEntity(HttpServletResponse.SC_CREATED, savedPostId);
    }

    @Override
    public ResponseEntity update(Map<String, String> paramMap, PostRequest postRequest) {
        Long postId = Long.parseLong(paramMap.get("postId"));

        postService.update(postId, postRequest);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(Map<String, String> paramMap) {
        Long postId = Long.parseLong(paramMap.get("postId"));

        postService.delete(postId);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }
}
