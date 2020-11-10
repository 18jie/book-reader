package com.graduation.bookreader.filter;

import com.graduation.bookreader.util.UserThreadLocalContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 22:01
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        StringBuffer requestURL = request.getRequestURL();
        Integer userId = (Integer) request.getSession().getAttribute("user");
        if(requestURL.toString().contains("auth") && userId != null){
            response.sendRedirect("/book-reader/login");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocalContext.clear();
    }
}
