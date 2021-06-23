package com.moneelab.assignment.domain.like;

public class Like {
    private Long id;
    private Long userId;
    private Long postId;

    private Like() {}

    /**
     * 새 좋아요 생성
     */
    public static Like createLike(Long postId, Long userId) {
        Like like = new Like();
        like.postId = postId;
        like.userId = userId;

        return like;
    }

    /**
     * 새 좋아요 id 설정
     */
    protected void initId(Long likeId) {
        this.id = likeId;
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }
}
