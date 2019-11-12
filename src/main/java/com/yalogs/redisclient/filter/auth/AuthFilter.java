package com.yalogs.redisclient.filter.auth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
@WebFilter(urlPatterns = "/self/**", filterName = "logFilter")
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("拦截到一个请求,{}", new Date());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 从header中获取aurh信息
        String auth = request.getHeader("auth");
        // 如果不存在ath字段，则去登录
        if (StringUtils.isBlank(auth)) {
            logger.info("{},的用户没登录，前去登录", request.getRemoteAddr());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
