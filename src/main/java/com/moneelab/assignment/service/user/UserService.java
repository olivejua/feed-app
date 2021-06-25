package com.moneelab.assignment.service.user;

import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse signUp(UserRequest userRequest);
    UserResponse signIn(UserRequest userRequest);
    List<String> getAllUsernames();
}
