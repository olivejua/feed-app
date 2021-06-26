package com.moneelab.assignment.domain.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Long save(Post post);
    void update(Long postId, String title, String content);
    void deleteById(Long postId);
    Optional<Post> findById(Long postId);
    List<Post> findAll();
    void clearAll();
}
