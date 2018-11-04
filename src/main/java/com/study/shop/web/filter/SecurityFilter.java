package com.study.shop.web.filter;

import com.study.shop.security.SecurityService;
import com.study.shop.security.entity.Session;
import com.study.shop.security.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private SecurityService securityService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        boolean isAuthRequired = true;

        logger.debug("start security filter for {}", httpServletRequest.getRequestURI());

        if (httpServletRequest.getRequestURI().startsWith("/login")) {
            logger.debug("skip security filter for {}", httpServletRequest.getRequestURI());
            isAuthRequired = false;
        }

        logger.debug(" must be skipped ");

        boolean isAuth = false;

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    String userToken = cookie.getValue();
                    Session session = securityService.getSession(userToken);
                    if (session != null && session.getUser().getUserRole() == UserRole.ADMIN) {
                        isAuth = true;
                    }
                    break;
                }
            }
        }

        if (isAuth || !isAuthRequired) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }

        logger.debug("end of security filter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Manual injecton of SecurityService");
        securityService = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext()).getBean(SecurityService.class);

    }

    @Override
    public void destroy() {

    }
}
