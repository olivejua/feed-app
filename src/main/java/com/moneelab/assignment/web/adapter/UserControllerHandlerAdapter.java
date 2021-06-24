package com.moneelab.assignment.web.adapter;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.user.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moneelab.assignment.util.PathConstants.USER_SIGN_IN;
import static com.moneelab.assignment.util.PathConstants.USER_SIGN_UP;

public class UserControllerHandlerAdapter extends HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof UserController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        UserController controller = (UserController) handler;

        String requestBody = inputStreamToString(request.getInputStream());

        ResponseEntity result;
        switch (request.getRequestURI()) {
            case USER_SIGN_UP:
                result = controller.signUp(objectMapper.readValue(requestBody, UserRequest.class));
                break;
            case USER_SIGN_IN:
                result = controller.signIn(objectMapper.readValue(requestBody, UserRequest.class), request.getSession());
                break;
            default:
                throw new IllegalArgumentException("존재하지 않는 경로입니다. uri=" + request.getRequestURI() + ", method=" + request.getMethod());
        }

        setHttpResponse(response, result);
    }
}
