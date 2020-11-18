package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @auther zoujialiang
 * @date 2020/11/18 18:07
 */
@Component
@Slf4j
public class MyFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //log.info("MyFilter2执行了=====================");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("过滤器222222222开始对请求进行预处理：");
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        if(!request.getRequestURI().contains("favicon.ico")){
//            String requestUri = request.getRequestURI();
//            System.out.println("请求的接口为：" + requestUri);
//            long startTime = System.currentTimeMillis();
//            //通过 doFilter 方法实现过滤功能
//            filterChain.doFilter(servletRequest, servletResponse);
//            // 上面的 doFilter 方法执行结束后用户的请求已经返回
//            long endTime = System.currentTimeMillis();
//            System.out.println("该用户的请求已经处理完毕，请求22222222222花费的时间为：" + (endTime - startTime));
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
