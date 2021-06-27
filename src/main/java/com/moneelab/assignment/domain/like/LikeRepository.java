package com.moneelab.assignment.domain.like;

import java.util.List;
import java.util.Optional;

public interface LikeRepository {
    Long save(Like like);
    void delete(Long postId, Long userId);
    List<Like> findLikesByPostId(Long postId);
    Optional<Like> findOneByPostIdAndUserId(Long postId, Long userId);
    Optional<Like> findOneById(Long likeId);
}
