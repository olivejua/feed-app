package com.moneelab.assignment.service.post;

import com.moneelab.assignment.domain.post.Post;
import com.moneelab.assignment.domain.post.PostRepository;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static com.moneelab.assignment.config.AppConfig.postRepository;
import static com.moneelab.assignment.config.AppConfig.userService;

public class PostServiceImpl implements PostService {

    /**
     * invoking a repository instance
     */
    private PostRepository postRepository = postRepository();
    private UserService userService = userService();

    /**
     * making it Singleton
     */
    private PostServiceImpl() {}
    private static final PostServiceImpl instance = new PostServiceImpl();

    public static PostServiceImpl getInstance( ) {
        return instance;
    }

    /**
     * processing business logic
     */
    @Override
    public synchronized Long save(PostRequest postRequest, Long authorId) throws NotExistException {
        //user 존재하는지 유효성 검사
        userService.findById(authorId);

        return postRepository.save(postRequest.toPost(authorId));
    }

    @Override
    public synchronized void update(Long postId, PostRequest postRequest) throws NotExistException {
        validate(postId);

        postRepository.update(
                postId, postRequest.getTitle(), postRequest.getContent());
    }

    @Override
    public synchronized void delete(Long postId) throws NotExistException {
        validate(postId);

        postRepository.deleteById(postId);
    }

    @Override
    public PostResponse findById(Long postId) throws NotExistException {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistException("해당 Post가 없습니다. postId="+postId));

        return new PostResponse(findPost);
    }

    @Override
    public List<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    private void validate(Long postId) throws NotExistException {
        //post 존재하는지 유효성 검사
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistException("해당 post가 없습니다. postId=" + postId));

        //user 존재하는지 유효성 검사
        userService.findById(post.getAuthorId());
    }
}