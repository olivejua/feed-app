package com.moneelab.assignment.config.filter;

import javax.servlet.*;
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
        System.out.println("loginFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
