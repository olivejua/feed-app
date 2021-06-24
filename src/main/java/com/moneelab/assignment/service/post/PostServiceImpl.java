package com.moneelab.assignment.service.post;

import com.moneelab.assignment.domain.post.Post;
import com.moneelab.assignment.domain.post.PostRepository;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;

import static com.moneelab.assignment.config.AppConfig.postRepository;

public class PostServiceImpl implements PostService {

    /**
     * invoking a repository instance
     */
    private PostRepository postRepository = postRepository();

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
    public synchronized Long save(PostRequest postRequest, Long authorId) {
        return postRepository.save(postRequest.toPost(authorId));
    }

    @Override
    public synchronized void update(Long postId, PostRequest postRequest) {
        postRepository.update(
                postId, postRequest.getTitle(), postRequest.getContent());
    }

    @Override
    public synchronized void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public synchronized PostResponse findById(Long postId) {
        Post findPost = postRepository.findById(postId);
        return new PostResponse(findPost);
    }
}
