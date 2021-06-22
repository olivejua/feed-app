package com.moneelab.assignment.web.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.user.UserController;

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

import static com.moneelab.assignment.util.PathConstants.*;

public class UserControllerHandlerAdapter implements HandlerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

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
                result = controller.signIn(objectMapper.readValue(requestBody, UserRequest.class));
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
