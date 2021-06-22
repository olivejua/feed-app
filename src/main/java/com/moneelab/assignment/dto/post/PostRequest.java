package com.moneelab.assignment.dto.post;

import com.moneelab.assignment.domain.post.Post;

public class PostRequest {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post toPost(Long authorId) {
        return Post.createPost(
                authorId, title, content);
    }
}
