package com.study.shop.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;


public class DummyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(DummyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("DummyFilter.doFilter() called");
        chain.doFilter(request, response);
        logger.debug("DummyFilter.doFilter() after chain");
    }

    @Override
    public void destroy() {

    }
}
