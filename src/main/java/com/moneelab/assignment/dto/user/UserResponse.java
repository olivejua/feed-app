package com.moneelab.assignment.dto.user;

import com.moneelab.assignment.domain.user.User;

import java.util.Date;

public class UserResponse {
    private Long userId;
    private String username;
    private Date joinedDate;

    public UserResponse(User user) {
        userId = user.getId();
        username = user.getName();
        joinedDate = user.getJoinedDate();
    }

    /**
     * Getter
     */
    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }
}
