package com.moneelab.assignment.web;

import com.moneelab.assignment.web.adapter.*;
import com.moneelab.assignment.web.controller.like.LikeControllerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.*;
import static com.moneelab.assignment.util.PathConstants.*;

@WebServlet(name = "frontControllerServlet", urlPatterns = COMMON_ALL+"/*")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServlet() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put(ALL_USERNAMES, userController());
        handlerMappingMap.put(USER_SIGN_UP, userController());
        handlerMappingMap.put(USER_SIGN_IN, userController());

        handlerMappingMap.put(FEED, feedController());

        handlerMappingMap.put(COMMON_POST, postController());
        handlerMappingMap.put(COMMON_COMMENT, commentController());
        handlerMappingMap.put(COMMON_LIKE, likeController());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new UserControllerHandlerAdapter());
        handlerAdapters.add(new FeedControllerHandlerAdapter());
        handlerAdapters.add(new PostControllerHandlerAdapter());
        handlerAdapters.add(new CommentControllerHandlerAdapter());
        handlerAdapters.add(new LikeControllerHandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HandlerAdapter adapter = getHandlerAdapter(handler);
        adapter.handle(request, response, handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }

        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }
}
