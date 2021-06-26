package com.moneelab.assignment.web.controller.user;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ErrorResponse;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.exception.WrongLoginInputException;
import com.moneelab.assignment.service.user.UserService;

import java.util.List;
import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.userService;
import static javax.servlet.http.HttpServletResponse.*;

public class UserControllerImpl implements UserController {

    /**
     * invoking a service instance
     */
    private UserService userService = userService();


    /**
     * making it Singleton
     */
    private UserControllerImpl() {}
    private static final UserControllerImpl instance = new UserControllerImpl();

    public static UserControllerImpl getInstance() {
        return instance;
    }


    /**
     * processing presentation logic
     */
    public ResponseEntity signUp(UserRequest userRequest) {
        userService.signUp(userRequest);
        return new ResponseEntity(SC_CREATED);
    }

    public ResponseEntity signIn(UserRequest userRequest, SessionUserService sessionService) {
        ResponseEntity response = null;

        try {
            UserResponse userResponse = userService.signIn(userRequest);
            sessionService.saveUser(userResponse);

            response = new ResponseEntity(SC_OK, userResponse);
        } catch (WrongLoginInputException we) {

            response = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(we.getMessage()));
        }
        return response;
    }

    public ResponseEntity logout(SessionUserService sessionService) {
        sessionService.removeUser();
        return new ResponseEntity(SC_NO_CONTENT);
    }

    public ResponseEntity getAllUsernames() {
        List<String> allUsernames = userService.getAllUsernames();
        return new ResponseEntity(SC_OK, allUsernames);
    }

    public ResponseEntity findUser(Map<String, String> paramMap) {
        Long userId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            UserResponse user = userService.findById(userId);

            result = new ResponseEntity(SC_OK, user);
        } catch (NotExistException ne) {
            result = new ResponseEntity(SC_BAD_REQUEST, new ErrorResponse(ne.getMessage()));
        }

        return result;
    }
}
