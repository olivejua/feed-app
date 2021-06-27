package com.moneelab.assignment.service.feed;

import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.feed.ContentResponse;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.service.comment.CommentService;
import com.moneelab.assignment.service.like.LikeService;
import com.moneelab.assignment.service.post.PostService;
import com.moneelab.assignment.service.user.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.moneelab.assignment.config.AppConfig.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedServiceTest {
    private UserService userService = userService();
    private PostService postService = postService();
    private CommentService commentService = commentService();
    private LikeService likeService = likeService();
    private FeedService feedService = feedService();

    private static int userCount = 400;

    @Test
    void testGetContents() {
        //given
        List<Long> ids = makeContent();

        //when
        List<ContentResponse> contents = feedService.getContents();

        //then
        assertEquals(30, contents.size());
        assertEquals(3, contents.get(0).getComments().size());
        assertEquals(5, contents.get(0).getLikeCount());
    }

    private List<Long>  makeContent() {
        List<Long> postIds = new ArrayList<>();

        for(int i=0; i<30; i++) {
            Long postId = savePost();
            postIds.add(postId);
            for (int j=0; j<3; j++) {
                saveComment(postId);
            }

            for (int j=0; j<5; j++) {
                Long userId = signUp();
                likeService.doLike(postId, userId);
            }
        }

        return postIds;
    }

    private Long saveComment(Long postId) {
        Long authorId = signUp();

        //requestDto 생성
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(postId);
        commentRequest.setContent("sample save content");

        //comment 저장
        return commentService.save(commentRequest, authorId);
    }

    private Long savePost() {
        Long authorId = signUp();

        //requestDto 생성
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("sample save title");
        postRequest.setContent("sample save content");

        //게시물 저장
        return postService.save(postRequest, authorId);
    }

    private Long signUp() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("sample post-sign in username" + (++userCount));
        userRequest.setPassword("sample post-sign in password" + (++userCount));

        return userService.signUp(userRequest);
    }
}
