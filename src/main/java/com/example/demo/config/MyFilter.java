package com.example.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @auther zoujialiang
 * @date 2020/11/18 17:48
 */
@Component
@Slf4j
//@WebFilter(filterName = "TimeCount" , urlPatterns = "/demo/wms-ware-info/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //log.info("初始化过滤器：", filterConfig.getFilterName());
    }

    @Override
    public void destroy() {
        //log.info("销毁过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对请求进行预处理
        //log.info("过滤器开始对请求进行预处理：");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(!request.getRequestURI().contains("favicon.ico")){
            String requestUri = request.getRequestURI();
            System.out.println("请求的接口为：" + requestUri);
            long startTime = System.currentTimeMillis();
            //通过 doFilter 方法实现过滤功能
            filterChain.doFilter(servletRequest, servletResponse);
            // 上面的 doFilter 方法执行结束后用户的请求已经返回
            long endTime = System.currentTimeMillis();
            System.out.println("该用户的请求已经处理完毕，请求花费的时间为：" + (endTime - startTime));
        }

    }
}
