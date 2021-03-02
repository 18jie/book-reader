package com.graduation.bookreader.filter;

import com.graduation.bookreader.util.UserThreadLocalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(LoginIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));//支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie跨域
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");//Origin, X-Requested-With, Content-Type, Accept,Access-Token

        StringBuffer requestURL = request.getRequestURL();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        logger.info("用户id{}", userId);
        if (userId != null) {
            UserThreadLocalContext.init(userId);
        }
        if (requestURL.toString().contains("auth") && userId != null) {
            response.sendRedirect("/book-reader/login");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocalContext.clear();
    }
}
