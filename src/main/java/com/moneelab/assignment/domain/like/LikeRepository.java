package com.moneelab.assignment.domain.like;

import java.util.List;
import java.util.Optional;

public interface LikeRepository {
    Long save(Like like);
    void delete(Long postId, Long userId);
    Optional<Like> findLike(Long postId, Long userId);
    List<Like> findLikesByPostId(Long postId);
    Optional<Like> findLikeByPostId(Long postId);
}
