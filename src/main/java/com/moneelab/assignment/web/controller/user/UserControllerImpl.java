package com.moneelab.assignment.web.controller.user;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.service.user.UserService;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.moneelab.assignment.config.AppConfig.userService;
import static javax.servlet.http.HttpServletResponse.*;

public class UserControllerImpl implements UserController {

    private UserService userService = userService();

    private UserControllerImpl() {}
    private static final UserControllerImpl instance = new UserControllerImpl();

    public static UserControllerImpl getInstance() {
        return instance;
    }

    public ResponseEntity signUp(UserRequest userRequest) {
        userService.signUp(userRequest);
        return new ResponseEntity(SC_CREATED);
    }

    public ResponseEntity signIn(UserRequest userRequest, SessionUserService sessionService) {
        UserResponse userResponse = userService.signIn(userRequest);
        sessionService.saveUser(userResponse);
        return new ResponseEntity(SC_OK, userResponse);
    }

    public ResponseEntity logout(SessionUserService sessionService) {
        sessionService.removeUser();
        return new ResponseEntity(SC_NO_CONTENT);
    }

    public ResponseEntity getAllUsernames() {
        List<String> allUsernames = userService.getAllUsernames();
        return new ResponseEntity(SC_OK, allUsernames);
    }
}
