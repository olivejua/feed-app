package com.moneelab.assignment.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ErrorResponse;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.exception.UnauthorizedException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

public class LoginFilter implements Filter {
    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    /**
     * [authorizing login user]
     * if any user exists in http session,
     * then continues a request.
     * unless prevent from protected resources and send a response has http status of UNAUTHORIZED.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        SessionUserService sessionService = new SessionUserService(httpRequest.getSession(false));

        if (!sessionService.existUserInSession()) {
            //
            ResponseEntity responseEntity =
                    new ResponseEntity(SC_UNAUTHORIZED,
                                        new ErrorResponse("리소스에 접근하려면 사용자 인증을 해야합니다."));

            HttpServletResponse httpResponse = (HttpServletResponse) response;
            responseEntity.setHttpResponse(httpResponse);

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
