package com.moneelab.assignment.web.adapter;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.user.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moneelab.assignment.util.HttpMethods.*;
import static com.moneelab.assignment.util.PathConstants.*;

public class UserControllerHandlerAdapter extends HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof UserController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        UserController controller = (UserController) handler;
        ResponseEntity result = null;

        String httpMethod = request.getMethod();
        if (httpMethod.equals(GET)) {
            switch (request.getRequestURI()) {
                case P_ALL_USERNAMES:
                    result = controller.getAllUsernames();
                    break;
                case P_USER:
                    result = controller.findUser(createParamMap(request));
                    break;
                case P_LOGOUT:
                    result = controller.logout(new SessionUserService(request.getSession()));
            }
        } else if (httpMethod.equals(POST)) {
            String requestBody = inputStreamToString(request.getInputStream());

            switch (request.getRequestURI()) {
                case P_SIGN_UP:
                    result = controller.signUp(objectMapper.readValue(requestBody, UserRequest.class));
                    break;
                case P_SIGN_IN:
                    SessionUserService sessionService = new SessionUserService(request.getSession());
                    result = controller.signIn(objectMapper.readValue(requestBody, UserRequest.class), sessionService);
                    break;
            }
        }

        if (result == null) {
            throw new IllegalArgumentException("요청하신 경로를 찾을 수 없습니다. uri=" + request.getRequestURI() + ", method=" + request.getMethod());
        }

        result.setHttpResponse(response);
    }
}
