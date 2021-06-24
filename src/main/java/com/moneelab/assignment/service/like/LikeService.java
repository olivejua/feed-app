package com.moneelab.assignment.service.like;

import com.moneelab.assignment.dto.like.LikeResponse;

public interface LikeService {
    Long doLike(Long postId, Long userId);
    void cancelLike(Long postId, Long userId);
    LikeResponse findLikeByPostId(Long postId);
}
