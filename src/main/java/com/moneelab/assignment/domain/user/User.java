package com.moneelab.assignment.domain.user;

import java.util.Date;

public class User {
    private Long id;
    private String name;
    private String password;
    private Date joinedDate;

    private User() {}

    /**
     * 새 User 생성
     */
    public static User createUser(String name, String password) {
        User user = new User();
        user.name = name;
        user.password = password;
        user.joinedDate = new Date();

        return user;
    }

    /**
     * 새 User id 설정
     */
    protected void initId(Long userId) {
        this.id = userId;
    }

    /**
     * Getter
     */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }
}
