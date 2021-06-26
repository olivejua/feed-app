package com.moneelab.assignment.web.controller.user;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.web.controller.Controller;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserController extends Controller {

    ResponseEntity signIn(UserRequest userRequest, SessionUserService sessionService);
    ResponseEntity signUp(UserRequest userRequest);
    ResponseEntity logout(SessionUserService sessionService);
    ResponseEntity getAllUsernames();
    ResponseEntity findUser(Map<String, String> paramMap);
}
