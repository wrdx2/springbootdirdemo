package com.wrdao.springboot.configuration;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFormAuthenticationFilter implements Filter {

    @Override
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(((HttpServletRequest) request).getRequestURI());
        if (subject.isAuthenticated() && subject.isPermitted(((HttpServletRequest) request).getRequestURI()))
            chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
