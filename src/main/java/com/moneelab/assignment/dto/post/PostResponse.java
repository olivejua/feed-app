package com.moneelab.assignment.dto.post;

import com.moneelab.assignment.domain.comment.Comment;
import com.moneelab.assignment.domain.post.Post;

import java.util.List;

public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private Long authorId;
    private List<Comment> comments;
    private int likeCount;
    private boolean loginUserLike;

    public PostResponse(Post post) {
        postId = post.getId();
        title = post.getTitle();
        content = post.getContent();
        authorId = post.getAuthorId();

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

    public List<Comment> getComments() {
        return comments;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public boolean isLoginUserLike() {
        return loginUserLike;
    }
}
