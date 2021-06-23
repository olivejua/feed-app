package com.moneelab.assignment.dto.comment;

import com.moneelab.assignment.domain.comment.Comment;

public class CommentRequest {
    Long postId;
    String content;

    /**
     * Getter & Setter
     */
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * changing CommentRequest To Comment
     */
    public Comment toComment(Long authorId) {
        return Comment.createComment(
                postId, authorId, content);
    }
}
