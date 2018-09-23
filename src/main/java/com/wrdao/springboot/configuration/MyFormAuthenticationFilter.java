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
        String url = ((HttpServletRequest) request).getRequestURI();

        if (subject.isAuthenticated() && subject.isPermitted(url)) {
            System.out.println("允许访问:" + url);
            chain.doFilter(request, response);
        } else {
            System.out.println("禁止访问:" + url);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
