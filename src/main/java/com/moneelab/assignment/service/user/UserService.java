package com.moneelab.assignment.service.user;

import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.exception.NotUniqueException;
import com.moneelab.assignment.exception.WrongLoginInputException;

import java.util.List;

public interface UserService {

    Long signUp(UserRequest userRequest) throws NotUniqueException ;
    UserResponse signIn(UserRequest userRequest) throws WrongLoginInputException;
    List<String> getAllUsernames();
    UserResponse findById(Long userId) throws NotExistException ;
}
