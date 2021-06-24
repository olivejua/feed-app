package com.moneelab.assignment.web.controller.post;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.service.post.PostService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.postService;

public class PostControllerImpl implements PostController {

    /**
     * invoking a service instance
     */
    private PostService postService = postService();

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
    //TODO 사용자인증 구현하면 세션으로 로그인사용자 주입하기
    @Override
    public ResponseEntity save(PostRequest postRequest, SessionUserService sessionService) {
        UserResponse currentUser = sessionService.getUser();

        Long savedPostId = postService.save(postRequest, currentUser.getUserId());
        return new ResponseEntity(HttpServletResponse.SC_CREATED, savedPostId);
    }

    @Override
    public ResponseEntity update(Map<String, String> paramMap, PostRequest postRequest, SessionUserService sessionService) {
        Long postId = Long.parseLong(paramMap.get("id"));

        validateAuthor(postId, sessionService);
        postService.update(postId, postRequest);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public ResponseEntity delete(Map<String, String> paramMap, SessionUserService sessionService) {
        Long postId = Long.parseLong(paramMap.get("id"));

        validateAuthor(postId, sessionService);
        postService.delete(postId);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }

    private void validateAuthor(Long postId, SessionUserService sessionService) {
        PostResponse post = postService.findById(postId);

        UserResponse currentUser = sessionService.getUser();
        if (!post.getAuthorId().equals(currentUser.getUserId())) {
            //TODO 에러
        }
    }
}
