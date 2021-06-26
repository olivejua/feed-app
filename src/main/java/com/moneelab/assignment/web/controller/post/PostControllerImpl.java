package com.moneelab.assignment.web.controller.post;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ErrorResponse;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.exception.NotAvailableException;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.post.PostService;

import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.postService;
import static com.moneelab.assignment.util.PathConstants.HOST;
import static com.moneelab.assignment.util.PathConstants.P_POST;
import static javax.servlet.http.HttpServletResponse.*;

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
    @Override
    public ResponseEntity save(PostRequest postRequest, SessionUserService sessionService) {
        UserResponse currentUser = sessionService.getUser();

        Long savedPostId = postService.save(postRequest, currentUser.getUserId());
        return new ResponseEntity(SC_CREATED, HOST+P_POST+"?id="+savedPostId);
    }

    @Override
    public ResponseEntity update(Map<String, String> paramMap, PostRequest postRequest, SessionUserService sessionService) {
        Long postId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            validateAuthor(postId, sessionService);
            postService.update(postId, postRequest);
            result = new ResponseEntity(SC_NO_CONTENT);

        } catch (NotAvailableException | NotExistException e) {
            result = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(e.getMessage()));
        }

        return result;
    }

    @Override
    public ResponseEntity delete(Map<String, String> paramMap, SessionUserService sessionService) {
        Long postId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            validateAuthor(postId, sessionService);
            postService.delete(postId);

            result = new ResponseEntity(SC_NO_CONTENT);
        } catch (NotAvailableException | NotExistException e) {
            result = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(e.getMessage()));
        }

        return result;
    }

    public ResponseEntity findPost(Map<String, String> paramMap) {
        Long postId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            PostResponse post = postService.findById(postId);

            result = new ResponseEntity(SC_OK, post);
        } catch (NotExistException ne) {
            result = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(ne.getMessage()));
        }

        return result;
    }

    private void validateAuthor(Long postId, SessionUserService sessionService) throws NotExistException, NotAvailableException {
        PostResponse post = postService.findById(postId);

        UserResponse currentUser = sessionService.getUser();
        if (!post.getAuthorId().equals(currentUser.getUserId())) {
            throw new NotAvailableException("로그인 사용자와 게시물 작성자가 일치하지 않습니다.");
        }
    }
}
