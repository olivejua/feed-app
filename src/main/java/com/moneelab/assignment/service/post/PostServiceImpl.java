package com.moneelab.assignment.service.post;

import com.moneelab.assignment.domain.post.Post;
import com.moneelab.assignment.domain.post.PostRepository;
import com.moneelab.assignment.domain.post.PostRepositoryImpl;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;

public class PostServiceImpl implements PostService {

    private PostRepository postRepository = PostRepositoryImpl.getInstance();

    private static final PostServiceImpl instance = new PostServiceImpl();

    public static PostServiceImpl getInstance( ) {
        return instance;
    }

    @Override
    public PostResponse save(PostRequest postRequest, Long authorId) {
        Post post =  postRepository.save(postRequest.toPost(authorId));
        return new PostResponse(post);
    }

    @Override
    public void update(Long postId, PostRequest postRequest) {

    }

    @Override
    public void delete(Long postId) {

    }

    @Override
    public PostResponse findById(Long postId) {
        return null;
    }
}
