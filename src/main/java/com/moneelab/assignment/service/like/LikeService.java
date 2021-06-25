package com.moneelab.assignment.service.like;

import com.moneelab.assignment.dto.like.LikeResponse;

import java.util.List;

public interface LikeService {
    Long doLike(Long postId, Long userId);
    void cancelLike(Long postId, Long userId);
    LikeResponse findOneByPostId(Long postId);
    List<LikeResponse> findLikesByPostId(Long postId);
}
