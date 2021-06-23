package com.moneelab.assignment.domain.comment;

import java.util.Date;

public class Comment {
    private Long id;
    private Long postId;
    private Long authorId;
    private String content;
    private Date createdDate;

    private Comment() {}

    /**
     * 새 댓글 생성
     */
    public static Comment createComment(Long postId, Long authorId, String content) {
        Comment comment = new Comment();
        comment.postId = postId;
        comment.authorId = authorId;
        comment.content = content;
        comment.createdDate = new Date();

        return comment;
    }

    /**
     * 새 댓글 id 설정
     */
    protected void initId(Long commentId) {
        this.id = commentId;
    }

    /**
     * 댓글 수정
     */
    protected void update(String content) {
        this.content = content;
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
