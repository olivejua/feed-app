package com.moneelab.assignment.dto.comment;

import com.moneelab.assignment.domain.comment.Comment;

import java.util.Date;

public class CommentResponse {
    private Long commentId;
    private String content;
    private Date createdDate;

    public CommentResponse(Comment comment) {
        commentId = comment.getId();
        content = comment.getContent();
        createdDate = comment.getCreatedDate();
    }

    /**
     * Getter
     */
    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}

