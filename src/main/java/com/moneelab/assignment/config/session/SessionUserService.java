package com.moneelab.assignment.config.session;

import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.exception.NotExistException;

import javax.servlet.http.HttpSession;

public class SessionUserService {

    private HttpSession session;
    private String currentUser = "currentUser";

    public SessionUserService(HttpSession session) {
        this.session = session;
    }

    public void saveUser(UserResponse userResponse) {
        session.setAttribute(currentUser, userResponse);
        session.setMaxInactiveInterval(60*30);
    }

    public void removeUser() {
        session.removeAttribute(currentUser);
    }

    public UserResponse getUser() throws NotExistException {
        if (!existUserInSession()) {
            throw new NotExistException("로그인이 되어 있지 않습니다. 로그인해주세요.");
        }
        
        return (UserResponse) session.getAttribute(currentUser);
    }

    public boolean existUserInSession() {
        return session != null && session.getAttribute(currentUser) != null;
    }
}
