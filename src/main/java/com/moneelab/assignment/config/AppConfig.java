package com.moneelab.assignment.config;

import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.domain.comment.CommentRepositoryImpl;
import com.moneelab.assignment.domain.post.PostRepository;
import com.moneelab.assignment.domain.post.PostRepositoryImpl;
import com.moneelab.assignment.service.comment.CommentService;
import com.moneelab.assignment.service.comment.CommentServiceImpl;
import com.moneelab.assignment.service.post.PostService;
import com.moneelab.assignment.service.post.PostServiceImpl;
import com.moneelab.assignment.web.controller.comment.CommentController;
import com.moneelab.assignment.web.controller.comment.CommentControllerImpl;
import com.moneelab.assignment.web.controller.post.PostController;
import com.moneelab.assignment.web.controller.post.PostControllerImpl;

public class AppConfig {

    /**
     * Post
     */
    public static PostController postController() {
        return PostControllerImpl.getInstance();
    }

    public static PostService postService() {
        return PostServiceImpl.getInstance();
    }

    public static PostRepository postRepository() {
        return PostRepositoryImpl.getInstance();
    }

    /**
     * Comment
     */
    public static CommentController commentController() {
        return CommentControllerImpl.getInstance();
    }

    public static CommentService commentService() {
        return CommentServiceImpl.getInstance();
    }

    public static CommentRepository commentRepository() {
        return CommentRepositoryImpl.getInstance();
    }
}
