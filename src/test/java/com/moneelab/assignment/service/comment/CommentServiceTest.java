package com.moneelab.assignment.service.comment;

import com.moneelab.assignment.domain.comment.Comment;
import com.moneelab.assignment.domain.comment.CommentRepository;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.post.PostService;
import com.moneelab.assignment.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.moneelab.assignment.config.AppConfig.*;
import static org.junit.jupiter.api.Assertions.*;

public class CommentServiceTest {

    private UserService userService = userService();
    private PostService postService = postService();
    private CommentService commentService = commentService();
    private CommentRepository commentRepository = commentRepository();

    private static int userCount = 0;

    @Test
    @DisplayName("CommentService: 댓글을 등록 요청한다")
    void testSave_success() {
        //given
        Long authorId = signUp();
        Long postId = savePost();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(postId);
        commentRequest.setContent("sample save content");

        //when
        Long commentId = commentService.save(commentRequest, authorId);

        Comment findComment = commentRepository.findById(commentId).orElse(null);

        //then
        assertNotNull(findComment);
        assertEquals(commentRequest.getContent(), findComment.getContent());
    }

    @Test
    @DisplayName("CommentService: 존재하지 않는 게시물의 댓글을 등록 요청한다")
    void testSave_fail_noneExistencePost() {
        //given
        Long authorId = signUp();
        Long noneExistentPostId = 1234L;

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(noneExistentPostId);
        commentRequest.setContent("sample save content");

        //when, then
        assertThrows(NotExistException.class,
                () -> commentService.save(commentRequest, authorId),
                "저장되어 있지 않은 게시물의 작성을 시도하면 NotExistException 예외가 발생해야 한다");
    }

    @Test
    @DisplayName("CommentService: 존재하지 않는 사용자가 댓글을 등록 요청한다")
    void testSave_fail_anonymousUser() {
        Long postId = savePost();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(postId);
        commentRequest.setContent("sample save content");

        Long anonymousUserId = 1234L;

        //when, then
        assertThrows(NotExistException.class,
                () -> commentService.save(commentRequest, anonymousUserId),
                "가입하지 않은 사용자가 댓글 작성을 시도하면 NotExistException 예외가 발생해야 한다");
    }

    @Test
    @DisplayName("CommentService: 댓글 수정을 정상 요청한다")
    void testUpdate_success() {
        //given
        Comment comment = saveComment();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(comment.getPostId());
        commentRequest.setContent("sample update content");

        //when
        commentService.update(comment.getId(), commentRequest);

        Comment findComment = commentRepository.findById(comment.getId()).orElse(null);

        //then
        assertNotNull(findComment);
        assertEquals(commentRequest.getContent(), findComment.getContent());
    }

    @Test
    @DisplayName("CommentService: 존재하지 않는 게시물의 댓글 수정 요청한다")
    void testUpdate_fail_noneExistentPost() {
        //given
        Comment comment = saveComment();
        
        //댓글의 게시물 삭제
        postService.delete(comment.getPostId());

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(comment.getPostId());
        commentRequest.setContent("sample update content");

        //when, then
        assertThrows(NotExistException.class,
                () -> commentService.update(comment.getId(), commentRequest),
                "존재하는 게시물의 댓글 수정 요청을 시도하면 NotExistException 예외가 발생해야한다");
    }

    @Test
    @DisplayName("CommentService: 댓글 삭제을 정상 요청한다")
    void testDelete_success() {
        //given
        Comment comment = saveComment();

        //when
        commentService.delete(comment.getId());

        Comment findComment = commentRepository.findById(comment.getId()).orElse(null);

        //then
        assertNull(findComment);
    }

    @Test
    @DisplayName("CommentService: 존재하지 않는 게시물의 댓글을 삭제 요청한다")
    void testDelete_fail_noneExistentPost() {
        //given
        Comment comment = saveComment();

        postService.delete(comment.getPostId());

        //when, then
        assertThrows(NotExistException.class,
                () -> commentService.delete(comment.getId()),
                "존재하지 않는 게시물의 삭제 요청을 시도하면 NotExistException 예외가 발생해야 한다");
    }

    @Test
    @DisplayName("CommentService: 존재하지 않는 댓글을 삭제 요청한다")
    void testDelete_fail_noneExistentComment() {
        //given
        Long noneExistentCommentId = 1234L;

        //when, then
        assertThrows(NotExistException.class,
                () -> commentService.delete(noneExistentCommentId),
                "저장되어 있지 않은 댓글을 삭제 요청 시도하면 NotExistException 예외가 발생해야한다");
    }

    private Comment saveComment() {
        Long authorId = signUp();
        Long postId = savePost();

        //requestDto 생성
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setPostId(postId);
        commentRequest.setContent("sample save content");
        
        //comment 저장
        Long commentId = commentService.save(commentRequest, authorId);

        return commentRepository.findById(commentId).get();
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
