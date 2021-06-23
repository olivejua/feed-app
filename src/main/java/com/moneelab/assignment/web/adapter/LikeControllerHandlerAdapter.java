package com.moneelab.assignment.web.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.util.HttpMethods;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.like.LikeController;

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

//TODO requestBody 객체 유효성 검사 (instanceof)
//TODO PathVariable 타입 검사
public class LikeControllerHandlerAdapter implements HandlerAdapter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof LikeController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        LikeController controller = (LikeController) handler;

        Map<String, String> paramMap = createParamMap(request);

        ResponseEntity result;
        switch (request.getMethod()) {
            case HttpMethods.POST:
                result = controller.doLike(paramMap);

                break;
            case HttpMethods.DELETE:
                result = controller.cancelLike(paramMap);

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
}
