package com.moneelab.assignment.web.adapter;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.feed.FeedController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moneelab.assignment.util.HttpMethods.GET;
import static com.moneelab.assignment.util.PathConstants.P_FEED;

public class FeedControllerHandlerAdapter extends HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof FeedController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        FeedController controller = (FeedController) handler;

        ResponseEntity result;
        if (request.getRequestURI().equals(P_FEED) && request.getMethod().equals(GET)) {
            result = controller.getContents();
        } else {
            throw new IllegalArgumentException("존재하지 않는 경로입니다. uri=" + request.getRequestURI() + ", method=" + request.getMethod());
        }

        setHttpResponse(response, result);
    }
}
