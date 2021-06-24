package com.moneelab.assignment.dto.comment;

import com.moneelab.assignment.domain.comment.Comment;

import java.util.Date;

public class CommentResponse {
    private Long commentId;
    private String content;
    private Long authorId;
    private Date createdDate;

    public CommentResponse(Comment comment) {
        commentId = comment.getId();
        content = comment.getContent();
        authorId = comment.getAuthorId();
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

    public Long getAuthorId() {
        return authorId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}

