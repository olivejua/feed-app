package com.moneelab.assignment.service.post;

import com.moneelab.assignment.domain.comment.Comment;
import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.domain.like.LikeRepository;
import com.moneelab.assignment.domain.post.Post;
import com.moneelab.assignment.domain.post.PostRepository;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.comment.CommentService;
import com.moneelab.assignment.service.like.LikeService;
import com.moneelab.assignment.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.moneelab.assignment.config.AppConfig.*;
import static org.junit.jupiter.api.Assertions.*;

public class PostServiceTest {

    private UserService userService = userService();
    private PostService postService = postService();
    private PostRepository postRepository = postRepository();

    private CommentService commentService = commentService();
    private CommentRepository commentRepository = commentRepository();
    private LikeService likeService = likeService();
    private LikeRepository likeRepository = likeRepository();

    private static int userCount = 100;

    @AfterEach()
    void cleanup() {
        likeRepository.clearAll();
        commentRepository.clearAll();
        postRepository.clearAll();
        userRepository().clearAll();
    }

    @Test
    @DisplayName("PostService: 작성한 게시물을 정상 등록 요청한다")
    void testSave_success() {
        //given
        Long authorId = signUp();

        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("sample save title1");
        postRequest.setContent("sample save content1");

        //when
        Long postId = postService.save(postRequest, authorId);

        //then
        assertNotNull(postId);
    }

    @Test
    @DisplayName("PostService: 가입되어 있지 않은 사용자가 게시물 등록 요청한다")
    void testSave_fail_noneExistentAuthor() {
        //given
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("sample save-noneExistentAuthor title1");
        postRequest.setContent("sample save-noneExistentAuthor content1");

        Long anonymousUserId = 1234L;

        //when, then
        assertThrows(NotExistException.class,
                () -> postService.save(postRequest, anonymousUserId),
                "가입된 사용자만 게시물을 작성할 수 있다");
    }

    @Test
    @DisplayName("PostService: 게시물을 수정 요청한다")
    void testUpdate_success() {
        //given
        Long postId = savePost();

        //수정 requestDto 생성
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("sample update title-updated");
        postRequest.setContent("sample update content-updated");

        //when
        postService.update(postId, postRequest);

        Post findPost = postRepository.findById(postId).orElse(null);

        //then
        assertNotNull(findPost);
        assertEquals(postRequest.getTitle(), findPost.getTitle());
        assertEquals(postRequest.getContent(), findPost.getContent());
    }

    @Test
    @DisplayName("PostService: 존재하지 않는 게시물을 수정 요청한다")
    void testUpdate_fail_noneExistentPost() {
        //given
        Long noneExistentPostId = 1234L;

        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("sample update title-updated");
        postRequest.setContent("sample update content-updated");

        //when, then
        assertThrows(NotExistException.class,
                () -> postService.update(noneExistentPostId, postRequest),
                "저장되어 있는 게시물만 수정이 가능하다");
    }

    @Test
    @DisplayName("PostService: 게시물을 삭제 요청한다")
    void testDelete_success() {
        //given
        Long postId = savePost();

        for (int i=0; i<3; i++) {
            saveComment(postId);
            likeService.doLike(postId, signUp());
        }

        //when
        postService.delete(postId);

        assertEquals(0, commentRepository.findCommentsByPostId(postId).size());
        assertEquals(0, likeRepository.findLikesByPostId(postId).size());

        Post findPost = postRepository.findById(postId).orElse(null);
        //then
        assertNull(findPost);
    }

    @Test
    @DisplayName("PostService: 존재하지 않는 게시물을 삭제 요청한다")
    void testDelete_fail_noneExistentPost() {
        //given
        Long noneExistentPostId = 1234L;

        //when, then
        assertThrows(NotExistException.class,
                () -> postService.delete(noneExistentPostId),
                "저장되어 있는 게시물만 삭제가 가능하다");
    }

    @Test
    @DisplayName("PostService: 모든 게시물을 가져온다")
    void testFindAll() {
        //given
        Long authorId = signUp();

        for (int i=1; i<=15; i++) {
            //requestDto 생성
            PostRequest postRequest = new PostRequest();
            postRequest.setTitle("sample save title"+i);
            postRequest.setContent("sample save content"+i);

            //게시물 저장
            postService.save(postRequest, authorId);
        }

        //when
        List<PostResponse> posts = postService.findAll();

        //then
        assertNotNull(posts);
        assertEquals(15, posts.size());
    }

    private Long saveComment(Long postId) {
        Long authorId = signUp();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(postId);
        commentRequest.setContent("sample save content");

        return commentService.save(commentRequest, authorId);
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
