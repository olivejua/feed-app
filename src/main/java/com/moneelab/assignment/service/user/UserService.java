package com.moneelab.assignment.service.user;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;

import javax.servlet.http.HttpSession;

public interface UserService {

    UserResponse signUp(UserRequest userRequest);
    UserResponse signIn(UserRequest userRequest, SessionUserService sessionService);
    void logout(SessionUserService sessionService);
}
