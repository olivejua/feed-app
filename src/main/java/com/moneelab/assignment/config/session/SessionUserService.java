package com.moneelab.assignment.config.session;

import com.moneelab.assignment.dto.user.UserResponse;

import javax.servlet.http.HttpSession;

public class SessionUserService {

    private HttpSession session;
    private String currentUser = "currentUser";

    public SessionUserService(HttpSession session) {
        this.session = session;
    }

    public void saveUser(UserResponse userResponse) {
        session.setAttribute(currentUser, userResponse);
    }

    public void removeUser() {
        session.removeAttribute(currentUser);
    }

    public boolean existUserInSession() {
        return session != null && session.getAttribute(currentUser) != null;
    }
}
