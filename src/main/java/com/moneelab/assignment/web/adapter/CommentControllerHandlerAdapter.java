package com.moneelab.assignment.web.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.util.HttpMethods;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.comment.CommentController;
import com.moneelab.assignment.web.controller.post.PostController;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CommentControllerHandlerAdapter implements HandlerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof CommentController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        CommentController controller = (CommentController) handler;

        Map<String, String> paramMap = createParamMap(request);
        String requestBody = inputStreamToString(request.getInputStream());

        String result = "";
        switch (request.getMethod()) {
            case HttpMethods.POST:
                result = controller.save(objectMapper.readValue(requestBody, CommentRequest.class));

                break;
            case HttpMethods.PUT:
                CommentRequest commentRequest = objectMapper.readValue(requestBody, CommentRequest.class);
                result = controller.update(paramMap, commentRequest);

                break;
            case HttpMethods.DELETE:
                result = controller.delete(paramMap);

                break;
            default:
                throw new IllegalArgumentException("존재하지 않는 경로입니다. uri=" + request.getRequestURI() + ", method=" + request.getMethod());
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(result);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

    private String inputStreamToString(ServletInputStream inputStream) {
        return new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
