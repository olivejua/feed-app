package com.moneelab.assignment.config.filter;

import com.moneelab.assignment.config.session.SessionUserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //do authorizing process
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        SessionUserService sessionService = new SessionUserService(httpRequest.getSession(false));

        if (!sessionService.existUserInSession()) {
            //TODO FORBIDDEN

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
