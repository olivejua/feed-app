package com.moneelab.assignment.service.post;

import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.dto.post.PostResponse;

public interface PostService {
    Long save(PostRequest postRequest, Long authorId);

    void update(Long postId, PostRequest postRequest);

    void delete(Long postId);

    PostResponse findById(Long postId);
}
