package com.graduation.bookreader.filter;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-12
 * Time: 14:47
 */
public class ReponseIntercepter extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //告诉浏览器允许所有的域访问
        //注意 * 不能满足带有cookie的访问,Origin 必须是全匹配
        //resp.addHeader("Access-Control-Allow-Origin", "*");
        //解决办法通过获取Origin请求头来动态设置
        String origin = request.getHeader("Origin");
        if (StringUtils.hasText(origin)) {
            response.addHeader("Access-Control-Allow-Origin", origin);
        }
        //允许带有cookie访问
        response.addHeader("Access-Control-Allow-Credentials", "true");
        //告诉浏览器允许跨域访问的方法
        response.addHeader("Access-Control-Allow-Methods", "*");
        //告诉浏览器允许带有Content-Type,header1,header2头的请求访问
        //resp.addHeader("Access-Control-Allow-Headers", "Content-Type,header1,header2");
        //设置支持所有的自定义请求头
        String headers = request.getHeader("Access-Control-Request-Headers");
        if (StringUtils.hasText(headers)) {
            response.addHeader("Access-Control-Allow-Headers", headers);
        }
        //告诉浏览器缓存OPTIONS预检请求1小时,避免非简单请求每次发送预检请求,提升性能
        response.addHeader("Access-Control-Max-Age", "3600");
        super.afterCompletion(request, response, handler, ex);
    }
}
