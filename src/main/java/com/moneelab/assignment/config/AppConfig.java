package com.moneelab.assignment.config;

import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.domain.comment.CommentRepositoryImpl;
import com.moneelab.assignment.domain.like.LikeRepository;
import com.moneelab.assignment.domain.like.LikeRepositoryImpl;
import com.moneelab.assignment.domain.post.PostRepository;
import com.moneelab.assignment.domain.post.PostRepositoryImpl;
import com.moneelab.assignment.domain.user.UserRepository;
import com.moneelab.assignment.domain.user.UserRepositoryImpl;
import com.moneelab.assignment.service.comment.CommentService;
import com.moneelab.assignment.service.comment.CommentServiceImpl;
import com.moneelab.assignment.service.feed.FeedService;
import com.moneelab.assignment.service.feed.FeedServiceImpl;
import com.moneelab.assignment.service.like.LikeService;
import com.moneelab.assignment.service.like.LikeServiceImpl;
import com.moneelab.assignment.service.post.PostService;
import com.moneelab.assignment.service.post.PostServiceImpl;
import com.moneelab.assignment.service.user.UserService;
import com.moneelab.assignment.service.user.UserServiceImpl;
import com.moneelab.assignment.web.controller.comment.CommentController;
import com.moneelab.assignment.web.controller.comment.CommentControllerImpl;
import com.moneelab.assignment.web.controller.feed.FeedController;
import com.moneelab.assignment.web.controller.feed.FeedControllerImpl;
import com.moneelab.assignment.web.controller.like.LikeController;
import com.moneelab.assignment.web.controller.like.LikeControllerImpl;
import com.moneelab.assignment.web.controller.post.PostController;
import com.moneelab.assignment.web.controller.post.PostControllerImpl;
import com.moneelab.assignment.web.controller.user.UserController;
import com.moneelab.assignment.web.controller.user.UserControllerImpl;

public class AppConfig {

    /**
     * Feed
     */
    public static FeedController feedController() {
        return FeedControllerImpl.getInstance();
    }

    public static FeedService feedService() {
        return FeedServiceImpl.getInstance();
    }

    /**
     * User
     */
    public static UserController userController() {
        return UserControllerImpl.getInstance();
    }

    public static UserService userService() {
        return UserServiceImpl.getInstance();
    }

    public static UserRepository userRepository() {
        return UserRepositoryImpl.getInstance();
    }

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

    /**
     * Like
     */
    public static LikeController likeController() {
        return LikeControllerImpl.getInstance();
    }

    public static LikeService likeService() {
        return LikeServiceImpl.getInstance();
    }

    public static LikeRepository likeRepository() {
        return LikeRepositoryImpl.getInstance();
    }
}
