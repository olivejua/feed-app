package com.moneelab.assignment.web.controller.user;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.service.user.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.moneelab.assignment.config.AppConfig.userService;

public class UserControllerImpl implements UserController {

    private UserService userService = userService();

    private UserControllerImpl() {}
    private static final UserControllerImpl instance = new UserControllerImpl();

    public static UserControllerImpl getInstance() {
        return instance;
    }

    public ResponseEntity signUp(UserRequest userRequest) {
        userService.signUp(userRequest);
        return new ResponseEntity(HttpServletResponse.SC_CREATED);
    }

    public ResponseEntity signIn(UserRequest userRequest, SessionUserService sessionService) {
        UserResponse userResponse = userService.signIn(userRequest, sessionService);
        return new ResponseEntity(HttpServletResponse.SC_OK, userResponse);
    }

    public ResponseEntity logout(SessionUserService sessionService) {
        userService.logout(sessionService);
        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }
}
