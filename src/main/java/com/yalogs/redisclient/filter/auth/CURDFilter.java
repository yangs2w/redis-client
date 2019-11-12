package com.yalogs.redisclient.filter.auth;


import com.yalogs.redisclient.factory.jedis.JedisFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
@WebFilter(urlPatterns = "/*", filterName = "crudFilter")
public class CURDFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String host = request.getParameter("host");
        if (StringUtils.isNotBlank(host)) {
            Jedis jedis4host = JedisFactory.getJedis4host(host);
            if (null != jedis4host) {
                // 更新JedisTerm
                JedisFactory.updateTermTime(host);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
