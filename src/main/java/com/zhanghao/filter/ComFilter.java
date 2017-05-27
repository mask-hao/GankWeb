package com.zhanghao.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanghao on 2017/5/12.
 */
@WebFilter(urlPatterns = "/*")
public class ComFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        response.setHeader("Cache-Control","max-age=0");
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
