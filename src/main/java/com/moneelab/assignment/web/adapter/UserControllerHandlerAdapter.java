package com.moneelab.assignment.web.adapter;

import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.user.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moneelab.assignment.util.PathConstants.*;

public class UserControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof UserController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        UserController controller = (UserController) handler;

        String result = "";
        switch (request.getRequestURI()) {
            case USER_SIGN_UP:
                result = controller.signup();
                break;
            case USER_SIGN_IN:
                result = controller.signin();
                break;
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 경로입니다.");
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(result);
    }
}
