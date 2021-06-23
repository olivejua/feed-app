package com.moneelab.assignment.dto.post;

import com.moneelab.assignment.domain.post.Post;

import java.util.Date;

public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private Long authorId;
    private Date createdDate;

    public PostResponse(Post post) {
        postId = post.getId();
        title = post.getTitle();
        content = post.getContent();
        authorId = post.getAuthorId();
        createdDate = post.getCreatedDate();
    }

    /**
     * Getter
     */
    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
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
