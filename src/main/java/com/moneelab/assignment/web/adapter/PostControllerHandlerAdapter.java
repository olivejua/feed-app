package com.moneelab.assignment.web.adapter;

import com.moneelab.assignment.util.HttpMethods;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.post.PostController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostControllerHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof PostController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        PostController controller = (PostController) handler;

        String result = "";
        switch (request.getMethod()) {
            case HttpMethods.POST:
                result = controller.save();
                break;
            case HttpMethods.PUT:
                result = controller.update();
                break;
            case HttpMethods.DELETE:
                result = controller.delete();
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
