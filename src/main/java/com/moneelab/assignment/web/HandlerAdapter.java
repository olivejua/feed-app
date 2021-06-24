package com.moneelab.assignment.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneelab.assignment.dto.ResponseEntity;

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

public abstract class HandlerAdapter {

    protected ObjectMapper objectMapper = new ObjectMapper();

    public abstract boolean supports(Object handler);

    public abstract void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

    protected void setHttpResponse(HttpServletResponse response, ResponseEntity result) throws IOException {
        response.setContentType(result.getContentType());
        response.setCharacterEncoding(result.getCharset());
        response.getWriter().write(objectMapper.writeValueAsString(result.getBody()));
    }

    protected Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

    protected String inputStreamToString(ServletInputStream inputStream) {
        return new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
