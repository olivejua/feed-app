package com.moneelab.assignment.service.post;

import com.moneelab.assignment.domain.post.Post;
import com.moneelab.assignment.domain.post.PostRepository;
import com.moneelab.assignment.domain.post.PostRepositoryImpl;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;

public class PostServiceImpl implements PostService {

    /**
     * invoking a repository instance
     */
    private PostRepository postRepository = PostRepositoryImpl.getInstance();

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
    public Long save(PostRequest postRequest, Long authorId) {
        Long postId =  postRepository.save(postRequest.toPost(authorId));
        return postId;
    }

    @Override
    public void update(Long postId, PostRequest postRequest) {
        postRepository.update(
                postId, postRequest.getTitle(), postRequest.getContent());
    }

    @Override
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostResponse findById(Long postId) {
        Post findPost = postRepository.findById(postId);
        return new PostResponse(findPost);
    }
}
