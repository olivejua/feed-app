package com.moneelab.assignment.web.adapter;

import com.moneelab.assignment.util.HttpMethods;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.like.LikeController;
import com.moneelab.assignment.web.controller.post.PostController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof LikeController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        LikeController controller = (LikeController) handler;

        String result = "";
        switch (request.getMethod()) {
            case HttpMethods.POST:
                result = controller.doLike();
                break;
            case HttpMethods.DELETE:
                result = controller.cancelLike();
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
