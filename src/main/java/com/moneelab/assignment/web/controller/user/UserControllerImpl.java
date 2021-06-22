package com.moneelab.assignment.web.controller.user;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.service.user.UserService;
import com.moneelab.assignment.service.user.UserServiceImpl;

import javax.servlet.http.HttpServletResponse;

public class UserControllerImpl implements UserController {

    private UserService userService = UserServiceImpl.getInstance();

    private UserControllerImpl() {}
    private static final UserControllerImpl instance = new UserControllerImpl();

    public static UserControllerImpl getInstance() {
        return instance;
    }

    public ResponseEntity signUp(UserRequest userRequest) {
        userService.signUp(userRequest);
        return new ResponseEntity(HttpServletResponse.SC_CREATED);
    }

    public ResponseEntity signIn(UserRequest userRequest) {
        UserResponse userResponse = userService.signIn(userRequest);
        return new ResponseEntity(HttpServletResponse.SC_OK, userResponse);
    }
}
