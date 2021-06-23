package com.moneelab.assignment.web;

import com.moneelab.assignment.web.adapter.CommentControllerHandlerAdapter;
import com.moneelab.assignment.web.adapter.LikeControllerHandlerAdapter;
import com.moneelab.assignment.web.adapter.PostControllerHandlerAdapter;
import com.moneelab.assignment.web.adapter.UserControllerHandlerAdapter;
import com.moneelab.assignment.web.controller.comment.CommentControllerImpl;
import com.moneelab.assignment.web.controller.like.LikeControllerImpl;
import com.moneelab.assignment.web.controller.post.PostControllerImpl;
import com.moneelab.assignment.web.controller.user.UserControllerImpl;

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
        handlerMappingMap.put(USER_SIGN_UP, UserControllerImpl.getInstance());
        handlerMappingMap.put(USER_SIGN_IN, UserControllerImpl.getInstance());

        handlerMappingMap.put(COMMON_POST, PostControllerImpl.getInstance());
        handlerMappingMap.put(COMMON_COMMENT, CommentControllerImpl.getInstance());
        handlerMappingMap.put(COMMON_LIKE, new LikeControllerImpl());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new UserControllerHandlerAdapter());
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
