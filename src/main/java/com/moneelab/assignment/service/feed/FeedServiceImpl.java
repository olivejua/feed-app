package com.moneelab.assignment.service.feed;

import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.dto.feed.ContentResponse;
import com.moneelab.assignment.dto.like.LikeResponse;
import com.moneelab.assignment.service.comment.CommentService;
import com.moneelab.assignment.service.like.LikeService;
import com.moneelab.assignment.service.post.PostService;

import java.util.List;
import java.util.stream.Collectors;

import static com.moneelab.assignment.config.AppConfig.*;

public class FeedServiceImpl implements FeedService {
    /**
     * invoking service instances
     */
    private PostService postService = postService();
    private CommentService commentService = commentService();
    private LikeService likeService = likeService();

    /**
     * making it Singleton
     */
    private FeedServiceImpl() {}
    private static final FeedServiceImpl instance = new FeedServiceImpl();

    public static FeedServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<ContentResponse> getContents() {
        return postService.findAll().stream()
                .map(post ->  {
                        List<CommentResponse> comments = commentService.findCommentsByPostId(post.getPostId());
                        List<LikeResponse> likes = likeService.findLikesByPostId(post.getPostId());
                        return new ContentResponse(post, comments, likes.size());
                }).collect(Collectors.toList());
    }
}
