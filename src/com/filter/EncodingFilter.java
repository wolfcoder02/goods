package com.filter;

import javax.servlet.*;
import java.io.IOException;

// 过滤器会在web服务器启动时创建实例
public class EncodingFilter implements Filter {
    private String econding;
    // 初始化，也只会执行1次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        econding = filterConfig.getInitParameter("encoding111");
        System.out.println("EncodingFilter init....");
    }

    // 这个方法是用于实现过滤功能
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        System.out.println("EncodingFilter doFilter....");
        // 设置请求对象的编码
        servletRequest.setCharacterEncoding(econding);
        // 设置响应对象的编码
        servletResponse.setCharacterEncoding(econding);
        servletResponse.setContentType("text/html;charset=utf-8");

        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println();
//        System.currentTimeMillis()
    }

    // 销毁资源：在web服务器关闭时执行
    @Override
    public void destroy() {
        System.out.println("EncodingFilter destroy....");
    }
}
