package com.moneelab.assignment.service.like;

public interface LikeService {
    Long doLike(Long postId, Long userId);
    void cancelLike(Long postId, Long userId);
}
