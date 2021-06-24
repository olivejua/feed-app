package com.moneelab.assignment.dto.like;

import com.moneelab.assignment.domain.like.Like;

public class LikeResponse {
    Long postId;
    Long userId;

    public LikeResponse(Like like) {
        this.postId = like.getPostId();
        this.userId = like.getUserId();
    }

    /**
     * Getter
     */
    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }
}
