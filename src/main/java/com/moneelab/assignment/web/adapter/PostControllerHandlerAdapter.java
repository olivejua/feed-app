package com.moneelab.assignment.web.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.post.PostRequest;
import com.moneelab.assignment.util.HttpMethods;
import com.moneelab.assignment.web.HandlerAdapter;
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

//TODO HandlerAdapter 중복되는 부분 합치기 (User, Post, Comment, Like)
//TODO requestBody 객체 유효성 검사 (instanceof)
//TODO PathVariable 타입 검사
public class PostControllerHandlerAdapter implements HandlerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof PostController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        PostController controller = (PostController) handler;

        Map<String, String> paramMap = createParamMap(request);
        String requestBody = inputStreamToString(request.getInputStream());

        ResponseEntity result;
        switch (request.getMethod()) {
            case HttpMethods.POST:
                result = controller.save(objectMapper.readValue(requestBody, PostRequest.class));

                break;
            case HttpMethods.PUT:
                PostRequest postRequest = objectMapper.readValue(requestBody, PostRequest.class);
                result = controller.update(paramMap, postRequest);

                break;
            case HttpMethods.DELETE:
                result = controller.delete(paramMap);

                break;
            default:
                throw new IllegalArgumentException("존재하지 않는 경로입니다. uri=" + request.getRequestURI() + ", method=" + request.getMethod());
        }

        response.setContentType(result.getContentType());
        response.setCharacterEncoding(result.getCharset());
        response.getWriter().write(objectMapper.writeValueAsString(result.getBody()));
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