package com.moneelab.assignment.domain.post;

import java.util.Date;

public class Post {
    private Long id;
    private Long authorId;
    private String title;
    private String content;
    private Date createdDate;

    private Post() {}

    /**
     * 새 게시물 생성
     */
    public static Post createPost(Long authorId, String title, String content) {
        Post post = new Post();
        post.authorId = authorId;
        post.title = title;
        post.content = content;
        post.createdDate = new Date();

        return post;
    }

    /**
     * 새 게시물 id 설정
     */
    protected void initId(Long postId) {
        this.id = postId;
    }

    /**
     * 게시물 수정
     */
    protected void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
