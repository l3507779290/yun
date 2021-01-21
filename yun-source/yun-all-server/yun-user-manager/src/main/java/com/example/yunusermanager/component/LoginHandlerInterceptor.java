package com.example.yunusermanager.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("username");
        System.out.println("preHandle----" + user + " ::: " + request.getRequestURL());
        boolean flag=true;
        if (user == null) {
            request.setAttribute("message","您还没有登录！！！请先登录！！！");
            // 获取request返回页面到登录页
            request.getRequestDispatcher("/user/login").forward(request, response);
            flag = false;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object user = request.getSession().getAttribute("username");
        System.out.println("afterCompletion----" + user + " ::: " + request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object user = request.getSession().getAttribute("username");
        System.out.println("afterCompletion----" + user + " ::: " + request.getRequestURL());
    }
}
