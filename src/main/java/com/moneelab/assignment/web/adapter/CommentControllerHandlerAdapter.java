package com.moneelab.assignment.web.adapter;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.comment.CommentRequest;
import com.moneelab.assignment.util.HttpMethods;
import com.moneelab.assignment.web.HandlerAdapter;
import com.moneelab.assignment.web.controller.comment.CommentController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CommentControllerHandlerAdapter extends HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof CommentController);
    }

    //TODO result:ResponseEntity null 체크하기
    //TODO result:ResponseEntity status별로 보내는것 체크하기
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        CommentController controller = (CommentController) handler;

        Map<String, String> paramMap = createParamMap(request);
        String requestBody = inputStreamToString(request.getInputStream());

        ResponseEntity result;
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

        setHttpResponse(response, result);
    }
}
