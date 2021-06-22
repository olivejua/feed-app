package com.moneelab.assignment.web.controller.user;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.web.controller.Controller;

public interface UserController extends Controller {

    ResponseEntity signIn(UserRequest userRequest);
    ResponseEntity signUp(UserRequest userRequest);
}
