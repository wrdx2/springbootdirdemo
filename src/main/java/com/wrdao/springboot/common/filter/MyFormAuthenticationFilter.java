package com.wrdao.springboot.common.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFormAuthenticationFilter implements Filter {

    private static Log logger = LogFactory.getLog(MyFormAuthenticationFilter.class);
    @Override
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Subject subject = SecurityUtils.getSubject();
        String url = ((HttpServletRequest) request).getRequestURI();

        if (subject.isAuthenticated() && subject.isPermitted(url)) {
            logger.debug("允许访问:" + url);
            chain.doFilter(request, response);
        } else {
            logger.debug("禁止访问:" + url);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
