package com.moneelab.assignment.dto.feed;

import com.moneelab.assignment.dto.comment.CommentResponse;
import com.moneelab.assignment.dto.post.PostResponse;

import java.util.List;

public class ContentResponse {
    PostResponse post;
    List<CommentResponse> comments;
    int likeCount;

    public ContentResponse(PostResponse post, List<CommentResponse> comments, int likeCount) {
        this.post = post;
        this.comments = comments;
        this.likeCount = likeCount;
    }

    /**
     * Getter
     */
    public PostResponse getPost() {
        return post;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
