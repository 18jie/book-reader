package com.graduation.bookreader.config;

import com.graduation.bookreader.util.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * 统一异常处理
 * <p>
 * Author: 丰杰
 * Date: 2020-10-29
 * Time: 17:15
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常处理
     */
    @ExceptionHandler
    public Result handleException(HttpServletRequest request, HttpServletResponse response, final Exception e) {
        LOGGER.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
//        if (e instanceof AlertException) {//可以在前端Alert的异常
//            if (((AlertException) e).getRetCode() != null) {//预定义异常
//                return new Result(((AlertException) e).getRetCode());
//            } else {
//                return Result.fail(e.getMessage());
//            }
//        } else {//其它异常
//            if (Util.isProduct()) {//如果是正式环境，统一提示
//                return Result.fail(e.getMessage());
//            } else {//测试环境，alert异常信息
//                return new Result(1, StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.toString());
//            }
//        }
    }

}