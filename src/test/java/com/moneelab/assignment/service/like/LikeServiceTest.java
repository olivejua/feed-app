package com.moneelab.assignment.service.like;

import com.moneelab.assignment.domain.like.Like;
import com.moneelab.assignment.domain.like.LikeRepository;
import com.moneelab.assignment.dto.like.LikeResponse;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.exception.NotAvailableException;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.post.PostService;
import com.moneelab.assignment.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.moneelab.assignment.config.AppConfig.*;
import static org.junit.jupiter.api.Assertions.*;

public class LikeServiceTest {
    private UserService userService = userService();
    private PostService postService = postService();
    private LikeService likeService = likeService();
    private LikeRepository likeRepository = likeRepository();

    private static int userCount = 300;

    @AfterEach()
    void cleanup() {
        likeRepository.clearAll();
        postRepository().clearAll();
        userRepository().clearAll();
    }

    @Test
    @DisplayName("LikeService: 좋아요를 정상 요청한다")
    void testDoLike_success() {
        //given
        Long userId = signUp();
        Long postId = savePost();

        //when
        Long likeId = likeService.doLike(postId, userId);

        LikeResponse like = likeService.findOneById(likeId);

        //then
        assertNotNull(like);
    }


    @Test
    @DisplayName("LikeService: 같은 사용자가 같은 게시물을 좋아요 2번 요청한다")
    void testDoLike_fail_alreadyExist() {
        //given
        Long userId = signUp();
        Long postId = savePost();

        likeService.doLike(postId, userId);

        //when, then
        assertThrows(NotAvailableException.class,
                () -> likeService.doLike(postId, userId),
                "좋아요를 2번 이상 요청하면 MotAvailable 예외가 발생해야 한다");
    }

    @Test
    @DisplayName("LikeService: 존재하지 않는 게시물에 좋아요 요청한다")
    void testDoLike_fail_noneExistentPost() {
        //given
        Long userId = signUp();
        Long postId = savePost();
        
        //Post 삭제
        postService.delete(postId);

        //when, then
        assertThrows(NotExistException.class,
                () -> likeService.doLike(postId, userId),
                "존재하지 않는 게시물에 좋아요를 요청하면 NotExistException이 발생해야 한다");
    }

    @Test
    @DisplayName("LikeService: 존재하지 않는 사용자가 좋아요 요청한다")
    void testDoLike_fail_anonymousUser() {
        //given
        Long anonymousUserId = 1234L;
        Long postId = savePost();

        //when, then
        assertThrows(NotExistException.class,
                () -> likeService.doLike(postId, anonymousUserId),
                "존재하지 않는 사용자가 좋아요를 요청하면 NotExistException이 발생해야 한다");
    }

    @Test
    @DisplayName("LikeService: 좋아요를 취소 요청한다")
    void testCancelLike_success() {
        //given
        Long userId = signUp();
        Long postId = savePost();

        Long likeId = likeService.doLike(postId, userId);

        //when
        likeService.cancelLike(postId, userId);

        Like findLike = likeRepository.findOneById(likeId).orElse(null);

        //then
        assertNull(findLike);
    }


    private Long savePost() {
        Long authorId = signUp();

        //requestDto 생성
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("sample save title1");
        postRequest.setContent("sample save content1");

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
